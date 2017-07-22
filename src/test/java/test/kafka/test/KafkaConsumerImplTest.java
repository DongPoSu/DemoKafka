package test.kafka.test;

import kafka.consumer.*;
import kafka.javaapi.consumer.ConsumerConnector;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.kafka.KafkaConsumerImpl;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * author: suzheng
 * version: v.1.0
 * package: test.kafka.test
 * company: SiBu
 * create_date: 2017/04/10
 * description :
 */
public class KafkaConsumerImplTest extends BaseTest {
    @Autowired
    private KafkaConsumerImpl consumer;

    @Test
    public void consumeTest() {
        consumer.consumeList(obj -> logger.info(obj.toString()));
    }

    @Test
    public void noSpringConsumerTestByYunGou() {

        Properties props = new Properties();
        props.put("zookeeper.connect", "YWS_CA_MQ_S1:2181,YWS_CA_MQ_S2:2181,YWS_CA_MQ_S3:2181");
        props.put("group.id", "1");
        props.put("auto.commit.enable", "true");
        props.put("zookeeper.session.timeout.ms", "30000");
        props.put("zookeeper.connection.timeout.ms", "30000");
        props.put("rebalance.backoff.ms", "4000");
        props.put("rebalance.max.retries", "10");
        props.put("zookeeper.sync.time.ms", "2000");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        ConsumerConfig config = new ConsumerConfig(props);
        ConsumerConnector consumer = Consumer.createJavaConsumerConnector(config);
        Whitelist whitelist = new Whitelist("test");
        List<KafkaStream<byte[], byte[]>>kafkaStreamList = consumer.createMessageStreamsByFilter(whitelist);

        for (KafkaStream<byte[], byte[]> kafkaStream : kafkaStreamList) {
            ConsumerIterator<byte[], byte[]> it = kafkaStream.iterator();
            while (it.hasNext()){
                System.out.println(it.next().toString());
            }
        }
    }
    @Test
    public void noSpringConsumerTest() {

        Properties originalProps = new Properties();
        originalProps.put("zookeeper.connect", "zk1:2181,zk2:2181,zk3:2181");
        originalProps.put("group.id", "1232");
        originalProps.put("zookeeper.session.timeout.ms", "10000");
        originalProps.put("zookeeper.sync.time.ms", "200");
        originalProps.put("auto.commit.interval.ms", "1000");
        originalProps.put("auto.offset.reset", "smallest");
        originalProps.put("serializer.class", "kafka.serializer.StringEncoder");

//        Properties originalProps = new Properties();

//        //zookeeper 配置，通过zk 可以负载均衡的获取broker
//        originalProps.put("zookeeper.connect", "zk1:2181,zk2:2181,zk3:2181");
//
//        //group 代表一个消费组
//        originalProps.put("group.id", "sz");
//
//        //zk连接超时时间
//        originalProps.put("zookeeper.session.timeout.ms", "10000");
//        //zk同步时间
//        originalProps.put("zookeeper.sync.time.ms", "200");
//        //自动提交间隔时间
//        originalProps.put("auto.commit.interval.ms", "1000");
//        //消息日志自动偏移量,防止宕机后数据无法读取
//        originalProps.put("auto.offset.reset", "smallest");
//        //序列化类
//        originalProps.put("serializer.class", "kafka.serializer.StringEncoder");
        ConsumerConnector consumer = Consumer.createJavaConsumerConnector(new ConsumerConfig(originalProps));
        Whitelist whitelist = new Whitelist("test1");
        List<KafkaStream<byte[], byte[]>>kafkaStreamList = consumer.createMessageStreamsByFilter(whitelist);

        for (KafkaStream<byte[], byte[]> kafkaStream : kafkaStreamList) {
            ConsumerIterator<byte[], byte[]> it = kafkaStream.iterator();
            while (it.hasNext()){
                System.out.println(it.next().toString());
            }
        }
    }
}
