package test.kafka;

import kafka.utils.VerifiableProperties;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;


/**
 * @ClassName:BeanSerializer.java
 * @Description:消息序列化
 * @author: zenglinhua
 * @date: 2015-10-22
 */
public class BeanSerializer implements Serializer {

    public BeanSerializer() {

    }

    @Override
    public void configure(Map configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, Object data) {
        return BeanUtils.object2Bytes(data);
    }

    @Override
    public void close() {

    }
}
