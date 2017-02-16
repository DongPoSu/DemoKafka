package test.kafka;

import kafka.producer.KeyedMessage;
import org.apache.kafka.clients.producer.*;
import org.apache.log4j.Logger;

import java.util.Properties;

/**
 * author: suzheng
 * version: v.1.0
 * package: test.kakaf
 * company: SiBu
 * create_date: 2017/02/15
 * description :
 */
public class KafkaProducerImpl implements IKafkaProducer {
    private static final Logger logger = Logger.getLogger(KafkaProducerImpl.class);
    private KafkaProducer<String, MessageBean> producer;
    private String topicName;
    private Properties properties;

    public void init() {
        producer = new KafkaProducer<>(properties);
    }

    @Override
    public boolean produce(String TOPIC, Object vo, String msgKey) {
        producer.send(new ProducerRecord<>(TOPIC, msgKey, getMessageBean(vo)),(metadata, exception) -> {
            // TODO: 2017/2/16 回调操作
            System.out.printf("生产回调操作："+ exception.getMessage());
        });
        logger.debug("kafka send TOPIC >>> " + TOPIC);
        destroy();
        return true;
    }

    private MessageBean getMessageBean(Object content) {
        final String nowTm = DateUtil.getCurrDateStr(DateUtil.DEFAULT_DATE_TIME_FORMAT);
        MessageBean msgBean = new MessageBean();
        msgBean.setContent(content);
        msgBean.setDatetimes(nowTm);
        return msgBean;
    }
    @Override
    public boolean produce(Object vo, String msgKey) {
        producer.send(new ProducerRecord<>(topicName, msgKey, getMessageBean(vo)),(metadata, exception) -> {
            // TODO: 2017/2/16 回调操作
        });
        logger.debug("kafka send TOPIC >>> " + topicName);
        return true;
    }

    public void destroy() {
        if (producer != null) {
            producer.close();
        }
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }


    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
