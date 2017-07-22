package test.kafka.test;


import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;
import kafka.serializer.Decoder;
import kafka.utils.VerifiableProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 自定义消息消费者
 * @author xiaojf 294825811@qq.com
 * @since 2015-7-15 下午11:10:28
 */
public class MyConsumer {
    private static  final String HASHLEAF_KAFKA_TOPIC  = "Order_MQ_REFUND_Topic";
    private final ConsumerConnector consumer;

    private MyConsumer(){
        Properties originalProps = new Properties();

        //zookeeper 配置，通过zk 可以负载均衡的获取broker
        originalProps.put("zookeeper.connect", "YWS_CA_MQ_S1:2181,YWS_CA_MQ_S2:2181,YWS_CA_MQ_S3:2181");

        //group 代表一个消费组
        originalProps.put("group.id", "test");

        //zk连接超时时间
        originalProps.put("zookeeper.session.timeout.ms", "10000");
        //zk同步时间
        originalProps.put("zookeeper.sync.time.ms", "200");
        //自动提交间隔时间
        originalProps.put("auto.commit.interval.ms", "1000");
        //消息日志自动偏移量,防止宕机后数据无法读取
        originalProps.put("auto.offset.reset", "smallest");
        //序列化类
        originalProps.put("serializer.class", "kafka.serializer.StringEncoder");

        //构建consumer connection 对象
        consumer = Consumer.createJavaConsumerConnector(new ConsumerConfig(originalProps));
    }

    private void consume(){
        //指定需要订阅的topic
        Map<String ,Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(HASHLEAF_KAFKA_TOPIC, 5);

        //指定key的编码格式
        Decoder<String> keyDecoder = new kafka.serializer.StringDecoder(new VerifiableProperties());
        //指定value的编码格式
        Decoder<String> valueDecoder = new kafka.serializer.StringDecoder(new VerifiableProperties());

        //获取topic 和 接受到的stream 集合
        Map<String, List<KafkaStream<String, String>>> map = consumer.createMessageStreams(topicCountMap, keyDecoder, valueDecoder);

        //根据指定的topic 获取 stream 集合
        List<KafkaStream<String, String>> kafkaStreams = map.get(HASHLEAF_KAFKA_TOPIC);

        ExecutorService executor = Executors.newFixedThreadPool(4);



        //因为是多个 message组成 message set ， 所以要对stream 进行拆解遍历
        for(final KafkaStream<String, String> kafkaStream : kafkaStreams){

            executor.submit(() -> {
                //拆解每个的 stream

                for (MessageAndMetadata<String, String> messageAndMetadata : kafkaStream) {

                    //messageAndMetadata 包括了 message ， topic ， partition等metadata信息
                    System.out.println("消费者 : " + messageAndMetadata.message() + "  partition :  " + messageAndMetadata.partition());

                }
            });


        }
    }

    public static void main(String[] args) {
        new MyConsumer().consume();
    }

}