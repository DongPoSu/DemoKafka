package test.kafka.test;

import kafka.producer.KeyedMessage;
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
import test.kafka.*;
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

public class KafkaProducerImplTest extends BaseTest{

    @Autowired
    private KafkaProducerImpl producer;
    private static  final String ORDER_MQ_REFUND_TOPIC = "Order_MQ_REFUND_Topic";

    @Test
    public void produceTest() {

        MQOrderBean mqOrderBean = new MQOrderBean();
        mqOrderBean.setMemberId("0835f0e05b3143b0b2f9d15efb5070ad");
        mqOrderBean.setOrderId("9629be0df05e4eaf8868038b3f3817c3");
        producer.produce(ORDER_MQ_REFUND_TOPIC, mqOrderBean);
    }





    @Test
    public void noSpringProduceTest() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.1.226:9092,192.168.1.228:9092,192.168.1.227:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        producer.produce(ORDER_MQ_REFUND_TOPIC,ORDER_MQ_REFUND_TOPIC,"test producer");

        final String nowTm = DateUtil.getCurrDateStr(DateUtil.YEAR_TO_SEC_UN_LINE);
//        MessageBean msgBean = new MessageBean();
//        MQOrderBean mqOrderBean = new MQOrderBean();
//        mqOrderBean.setMemberId(memberId);
//        mqOrderBean.setOrderId(orderId);
//        msgBean.setContent(vo);
//        msgBean.setDatetimes(nowTm);
//        KeyedMessage<String, MessageBean> km = new KeyedMessage<String, MessageBean>(topicName,topicName,msgBean);
//        producer.send(km);

    }


}
