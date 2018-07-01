package me.pingcai.kafka;

import me.pingcai.avro.User;
import org.apache.avro.Schema;

import java.util.EnumSet;

/**
 * Created by pingcai at 2018/7/1 12:25
 */
public enum KafkaTopicEnum {

    USER("user-info-topic", User.getClassSchema());

    public final String topicName;
    public final Schema topicSchema;

    KafkaTopicEnum(String topicName, Schema topicSchema) {
        this.topicName = topicName;
        this.topicSchema = topicSchema;
    }

    public static KafkaTopicEnum matchFor(String topicName) {
        return EnumSet.allOf(KafkaTopicEnum.class).stream()
                .filter(topic -> topic.topicName.equals(topicName))
                .findFirst()
                .orElse(null);
    }
}