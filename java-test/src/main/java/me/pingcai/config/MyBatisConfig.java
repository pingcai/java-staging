package me.pingcai.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Created by pingcai at 2018/5/15 21:52
 */
@Configuration
@MapperScan("me.pingcai.dao.mapper")
public class MyBatisConfig {

    @Resource
    DataSource dataSource;

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() {

        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);

        return sessionFactoryBean;
    }


}
