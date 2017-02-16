package test.kafka.test;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.PartitionInfo;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import test.kafka.IDealMessage;
import test.kafka.KafkaConsumerImpl;
import test.kafka.KafkaProducerImpl;
import test.kafka.MessageBean;
import test.kafka.constant.KafkaTopic;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * author: suzheng
 * version: v.1.0
 * package: test.kafka.test
 * company: SiBu
 * create_date: 2017/02/15
 * description :
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application.xml"})
public class KafkaProducerImplTest {
    private static final Logger logger = Logger.getLogger(KafkaProducerImplTest.class);
    @Autowired
    private KafkaProducerImpl producer;
    @Autowired
    private KafkaConsumerImpl consumer;

    @Test
    public void produceTest() {
        producer.produce("test", "我了个去，生产个日志", "produce");
    }

    @Test
    public void consumeTest() {
        consumer.consumeList("test", new IDealMessage<MessageBean>() {
            @Override
            public void dealMQ(MessageBean obj) {
                logger.info("消费：" + obj.getId() + "," + obj.getDatetimes() + "," + obj.getContent() + "," + obj.getName());
            }
        });
    }

    @Test
    public void noSpringConsumerTest() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.1.111:9092");
//        props.put("group.id", "1");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("test"));
        while (true) {
            Map<String, List<PartitionInfo>> listMap = consumer.listTopics();
            ConsumerRecords<String, String> records = consumer.poll(10);
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());

        }
    }

    @Test
    public void noSpringProduceTest() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.1.111:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
//        for(int i = 0; i < 100; i++)
            producer.send(new ProducerRecord<String, String>("test", "order", "order has payed"), (metadata, exception) -> {
                System.out.printf("生产回调操作 no spring:" + exception.getMessage());
            });
        producer.close();

    }
}
