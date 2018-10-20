## 说明

JAVA WEB 开发脚手架，集成常见的组件

- Jetty
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

导入`java_staging.sql`

### 测试

```    
run/debug JettyBoot

```

## 部署

### 配置

1. 设置环境变量(dev/test/staging/prod)

    export profile='pord' // 声明当前环境为生产环境

2. 设置运行日志

    env.properties -> log4j2.log.path

3. 设置Jetty参数

    含义见JettyBoot, 并修改 java-staging/java-test/deploy/build.sh

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