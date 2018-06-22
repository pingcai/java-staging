package me.pingcai.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by pingcai at 2018/6/12 23:09
 */
@Configuration
@MapperScan("me.pingcai.dao.mapper")
public class MyBatisConfig {

}
