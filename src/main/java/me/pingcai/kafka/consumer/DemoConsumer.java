package me.pingcai.kafka.consumer;

import com.google.common.collect.Lists;
import me.pingcai.kafka.KafkaTopicEnum;
import me.pingcai.kafka.deserializer.UserDeserializer;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * Created by pingcai at 2018/7/1 12:00
 */
public class DemoConsumer<T extends SpecificRecordBase> {

    private KafkaConsumer<String, T> consumer = new KafkaConsumer<String, T>(getProperties());

    public List<T> consume(KafkaTopicEnum topic) {
        consumer.subscribe(Collections.singleton(topic.topicName));
        ConsumerRecords<String, T> records = consumer.poll(1000);
        List<T> data = Lists.newArrayList();
        records.forEach((record) -> {
            data.add(record.value());
        });
        return data;
        /*return StreamSupport.stream(records.spliterator(), false)
                .map(ConsumerRecord::value).collect(Collectors.toList());*/
    }


    private Properties getProperties() {

        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "DemoConsumer");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                UserDeserializer.class.getName());
        return props;
    }

}
