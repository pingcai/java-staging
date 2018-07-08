package me.pingcai.config;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by pingcai at 2018/6/12 23:09
 */
@Configuration
@MapperScan("me.pingcai.dao.mapper")
public class MyBatisConfig {

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(@Autowired DataSource dataSource) throws IOException {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();

        bean.setDataSource(dataSource);

        // 设置查找器
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        // 自动扫描mybatis文件
        bean.setMapperLocations(resolver.getResources("classpath:/mybatis/mapper/*.xml"));
        bean.setConfigLocation(resolver.getResource("classpath:/mybatis/sqlsession-config.xml"));

        bean.setTypeAliasesPackage("me.pingcai.dao.entity");

        // PageHelper 插件
        PageInterceptor interceptor = new PageInterceptor();
        interceptor.setProperties(getPageHelperProperties());
        bean.setPlugins(new Interceptor[]{interceptor});

        return bean;
    }

    private Properties getPageHelperProperties() {
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql");
        return properties;
    }
}

