### 说明

SSM 开发脚手架，集成常见的组件

### 使用

扒代码

    git clone git@github.com:pingcai/java-staging.git
    
    cd java-staging

修改配置

    java-test/src/main/resources/conf/dev/config.properties

测试数据库

```
mysql -e 'CREATE DATABASE java_staging DEFAULT CHARSET utf8mb4;USE java_staging; \
CREATE TABLE user(id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,status TINYINT NOT NULL,name VARCHAR(32) NOT NULL,sex BIT NOT NULL,age TINYINT NOT NULL,birthday DATETIME NOT NULL DEFAULT NOW(), comment VARCHAR(255) DEFAULT '')
' -h localhost -uroot -p
```


测试

```    
mvn jetty:run

curl -X GET http://127.0.0.1:7878/alive

```
