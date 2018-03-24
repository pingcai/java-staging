## 说明

SSM 开发脚手架，集成:

- UEditor
- 七牛CDN

其中修改`ueditor.config.js`的`serverUrl`接管UEditor处理图片上传的`controller.jsp`,方便定制自己的功能.

## 使用

### 拉代码

    git clone git@github.com:pingcai/java-staging.git
    
    cd java-staging

### 修改配置

    java-ueditor/src/main/resources/conf/dev/config.properties

### 建立测试数据库

```
mysql -e 'create database java_staging default charset utf8mb4;use java_staging; \
create table test(id bigint primary key auto_increment,name varchar(32),age tinyint);' -h localhost -uroot -p
```

### 运行

    mvn jetty:run

### 测试

```    
http://localhost:7878/admin/index
```

## 参考

http://fex.baidu.com/ueditor/#server-path

http://www.jfinal.com/share/142