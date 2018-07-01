package me.pingcai;

import lombok.extern.slf4j.Slf4j;
import me.pingcai.avro.User;
import me.pingcai.kafka.KafkaTopicEnum;
import me.pingcai.kafka.consumer.DemoConsumer;
import me.pingcai.kafka.producer.DemoProducer;
import org.junit.Test;

import java.util.List;
import java.util.Random;

/**
 * Created by pingcai at 2018/7/1 12:48
 */
@Slf4j
public class KafkaTests {

    @Test
    public void consume() {
        DemoConsumer<User> consumer = new DemoConsumer<>();
        final int counter = 100;
        for (int i = 0; i < counter; i++) {
            List<User> users = consumer.consume(KafkaTopicEnum.USER);
            if (users.isEmpty()) {
                System.out.println("Consumer received nothing");
            } else {
                users.forEach(user -> log.info("\nConsumer received user: {}\n", user));
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void produce() {
        final DemoProducer<User> producer = new DemoProducer<>();
        final int counter = 100;
        final Random random = new Random();
        for (int i = 0; i < counter; i++) {
            producer.produce(KafkaTopicEnum.USER, new User("Tom", random.nextInt(), "Address: " + random.nextInt()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
