package test.kafka;

import kafka.consumer.KafkaStream;
import kafka.consumer.Whitelist;
import kafka.message.MessageAndMetadata;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.List;
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
        consumer.commitSync();
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
        consumer.subscribe(Arrays.asList(topicName));
        while (true) {
            ConsumerRecords<String, MessageBean> records = consumer.poll(1000);
            for (ConsumerRecord<String, MessageBean> record : records)
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
        }
    }

}
