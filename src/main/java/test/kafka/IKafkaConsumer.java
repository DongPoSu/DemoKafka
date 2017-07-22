package test.kafka;

/**
 * author: suzheng
 * version: v.1.0
 * package: test.kafka
 * company: SiBu
 * create_date: 2017/02/15
 * description :
 */
public interface IKafkaConsumer {
    /**
     * @param TOPIC 指定主题
     * @description:接收指定主题消息列表
     * @author zenglinhua  2015-10-22
     */
    void consumeList(String TOPIC, IDealMessage dealMessage);

    /**
     * @description:接收配置主题消息列表
     * @author zenglinhua  2015-10-22
     */
    void consumeList(IDealMessage dealMessage);

    /**
     * @description:提交消费确认
     * @author zenglinhua  2015-10-22
     */
    void commitOffsets();

    /**
     * @description:销毁接收实例
     * @author zenglinhua  2015-10-22
     */
    void destroy();
}
