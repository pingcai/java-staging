### 说明

- AvroTests 测试Avro序列化和反序列化

- KafkaTests 测试通过Avro完成生产者消费者问题

### 搭建ZooKeeper和Kafka

https://yanbin.blog/zookeeper-fast-get-started/

https://yanbin.blog/initial-apache-kafka-environment/

### 生成Avro实体

    java -jar D:/java/apache-maven-3.5.3/store/org/apache/avro/avro-tools/1.8.2/avro-tools-1.8.2.jar compile schema user.avsc .

或

    mvn avro:schema 后将生成的类移动到相应的**package**下

### 其它

#### 找不到 DESKTOP-SGV689U.localdomain 主机

修改运行程序的机器的**host**

#### 修改Kafka的日志路径

修改**config/server.properties**的**log.dirs**

#### ZooKeeper监控

介绍：`https://www.cnblogs.com/likehua/p/3999588.html`

    echo stat | nc localhost 2181