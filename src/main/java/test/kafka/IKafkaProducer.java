package test.kafka;

/**
 * author: suzheng
 * version: v.1.0
 * package: test.kakaf
 * company: SiBu
 * create_date: 2017/02/15
 * description :
 */
interface IKafkaProducer {
    boolean produce(String TOPIC, Object vo, String msgKey);
    boolean produce(Object vo, String msgKey);
    boolean produce(String TOPIC,Object vo);
    void destroy();
}
