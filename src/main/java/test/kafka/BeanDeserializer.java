package test.kafka;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;



public class BeanDeserializer implements Deserializer {

    public BeanDeserializer() {
    }

    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public Object deserialize(String topic, byte[] data) {
        return BeanUtils.object2Bytes(data);
    }

    @Override
    public void close() {

    }
}
