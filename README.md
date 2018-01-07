### 说明

SSM 开发脚手架，集成常见的组件

### 使用

扒代码

    git clone git@github.com:pingcai/java-staging.git

修改配置

    java-test/src/main/resources/conf/dev/config.properties

测试

```    
cd 项目根目录

mysql -e 'create database java_staging default charset utf8mb4;use java_staging; \
create table test(id bigint primary key auto_increment,name varchar(32),age tinyint);' -h localhost -uroot -p

mvn jetty:run

curl -X GET http://127.0.0.1:7878/alive

```