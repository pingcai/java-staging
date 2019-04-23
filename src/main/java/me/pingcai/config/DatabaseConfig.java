package me.pingcai.config;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author huangpingcai
 * @since 2019/4/23 00:16
 */
@Slf4j
@Configuration
@MapperScan("me.pingcai.dao.mapper")
public class DatabaseConfig {

}
