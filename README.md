## 说明

JAVA WEB 开发脚手架，集成常见的组件

- Spring
- Mybatis
- Slf4j + Log4j2

## 开发

### 拉取代码

    git clone git@github.com:pingcai/java-staging.git
    
    cd java-staging

### 修改配置

    java-staging/java-test/src/main/profile/dev/env.properties

### 数据库

```
mysql -e 'CREATE DATABASE java_staging DEFAULT CHARSET utf8mb4;USE java_staging; \
CREATE TABLE user(id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,status TINYINT UNSIGNED NOT NULL,name VARCHAR(32) NOT NULL,sex TINYINT UNSIGNED NOT NULL,age TINYINT UNSIGNED NOT NULL,birthday DATETIME DEFAULT NOW(), comment VARCHAR(255) DEFAULT '', register_ip INT UNSIGNED NOT NULL)
' -h localhost -uroot -p
```


### 测试

```    
mvn jetty:run

curl -X GET http://127.0.0.1:7878/alive

或

IDEA 右侧 Maven 插件

```

## 部署

### 配置

1. 选择环境(dev/test/staging/prod)

    export profile='pord'

2. 运行日志

    env.properties -> log4j2.log.path

3. Jetty参数

    含义见 JettyBoot, 并修改 java-staging/java-test/deploy/build.sh

### 构建

    java-staging/java-test/deploy/build.sh

### 运行

    java-staging/java-test/deploy/run.sh

## 其它
禁用ipv6
```
MAVEN_OPTS='-Xmx1024m -Xmx1024m -Djava.net.preferIPv4Stack=true'
export MAVEN_OPTS
```