package me.pingcai.kafka.deserializer;

import me.pingcai.kafka.KafkaTopicEnum;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

/**
 * Created by pingcai at 2018/7/1 12:03
 */
public class UserDeserializer<T extends SpecificRecordBase> implements Deserializer {
    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public Object deserialize(String topic, byte[] data) {
        DatumReader<T> userDatumReader = new SpecificDatumReader<>(KafkaTopicEnum.matchFor(topic).topicSchema);
        BinaryDecoder binaryEncoder = DecoderFactory.get().directBinaryDecoder(new ByteArrayInputStream(data), null);
        try {
            return userDatumReader.read(null, binaryEncoder);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void close() {

    }
}
