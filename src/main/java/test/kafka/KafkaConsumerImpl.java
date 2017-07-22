package test.kafka;

import kafka.consumer.Whitelist;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

/**
 * author: suzheng
 * version: v.1.0
 * package: test.kafka
 * company: SiBu
 * create_date: 2017/02/15
 * description :
 */
public class KafkaConsumerImpl implements IKafkaConsumer {
    private Properties properties;

    private KafkaConsumer<String, MessageBean> consumer;

    private String topicName;


    public void init() {
        consumer = new KafkaConsumer<>(properties);
    }

    @Override
    public void consumeList(String TOPIC, IDealMessage dealMessage) {
        loopConvertMessage(TOPIC, dealMessage);
    }

    @Override
    public void consumeList(IDealMessage dealMessage) {
        loopConvertMessage(topicName, dealMessage);
    }

    @Override
    public void commitOffsets() {
        consumer.commit(true);
    }

    @Override
    public void destroy() {
        if (consumer != null) {
            consumer.close();
        }
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    private void loopConvertMessage(String topicName, IDealMessage dealMessage) {
        consumer.subscribe(topicName);
        while (true) {
//            Whitelist whitelist = new Whitelist("test");
//             consumer.createMessageStreamsByFilter(whitelist);

//            Map<String, ConsumerRecords<String, MessageBean>> records = consumer.poll(1000);
//            if(records != null){
//                System.out.println(records.toString());
//            }
//            records.toString();
//            for (ConsumerRecord<String, MessageBean> record : records)
//                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
        }
    }

}
