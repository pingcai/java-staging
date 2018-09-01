### 说明

JAVA WEB 开发脚手架，集成常见的组件

- Spring
- Mybatis
- Slf4j + Log4j2

### 使用

拉取代码

    git clone git@github.com:pingcai/java-staging.git
    
    cd java-staging

修改配置

    java-test/src/main/resources/conf/dev/config.properties

数据库

```
mysql -e 'CREATE DATABASE java_staging DEFAULT CHARSET utf8mb4;USE java_staging; \
CREATE TABLE user(id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,status TINYINT UNSIGNED NOT NULL,name VARCHAR(32) NOT NULL,sex TINYINT UNSIGNED NOT NULL,age TINYINT UNSIGNED NOT NULL,birthday DATETIME DEFAULT NOW(), comment VARCHAR(255) DEFAULT '', register_ip INT UNSIGNED NOT NULL)
' -h localhost -uroot -p
```


测试

```    
mvn jetty:run

curl -X GET http://127.0.0.1:7878/alive

或

IDEA 右侧 Maven 插件

```

### 其它
禁用ipv6
```
MAVEN_OPTS='-Xmx1024m -Xmx1024m -Djava.net.preferIPv4Stack=true'
export MAVEN_OPTS
```