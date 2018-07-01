package me.pingcai.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import me.pingcai.kafka.KafkaTopicEnum;
import me.pingcai.kafka.serializer.UserSerializer;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * Created by pingcai at 2018/7/1 11:36
 */
@Slf4j
public class DemoProducer<T extends SpecificRecordBase> {

    private KafkaProducer<String, T> producer = new KafkaProducer<String, T>(getProperties());

    public void produce(KafkaTopicEnum topic, T data) {
        producer.send(new ProducerRecord<>(topic.topicName, data), (metadata, exception) -> {
            if (exception != null) {
                log.error("Send fail ！", exception);
            } else {
                log.info("Send success ！{}", data);
            }
        });
    }


    private Properties getProperties() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.CLIENT_ID_CONFIG, DemoProducer.class.getName());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, UserSerializer.class.getName());
        return props;
    }
}
