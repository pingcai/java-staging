## 说明

JAVA WEB 开发脚手架，集成常见的组件

- Spring Boot
- Mybatis
- Slf4j + Log4j2

## 开发

### 拉取代码

    git clone git@github.com:pingcai/java-staging.git
    
    cd java-staging

### 修改配置

    src/main/resources/application.yml

### 数据库

导入`java_staging.sql`

### 测试

```    
run/debug Application.java

```

## 部署

### 配置

1. 设置环境变量(dev/test/staging/prod)
```
export SPRING_PROFILES_ACTIVE='pord' // 声明当前环境为生产环境
```
2. 设置运行日志
```
src/main/resources/log4j2.xml

log.path=日志位置

app.name=日志名称
```

### 构建

    deploy/build.sh

### 运行

    deploy/run.sh

## 其它
禁用ipv6
```
MAVEN_OPTS='-Xmx1024m -Xmx1024m -Djava.net.preferIPv4Stack=true'
export MAVEN_OPTS
```