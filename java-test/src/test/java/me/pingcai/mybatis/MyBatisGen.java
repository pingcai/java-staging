package me.pingcai.mybatis;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * MyBatis 实体生成
 * Created by pingcai at 2018/5/15 14:58
 */
public class MyBatisGen {

    public static void main(String[] args) throws Exception {

        String configPath = "C:\\Users\\pingcai\\IdeaProjects\\java-staging\\java-test\\src\\test\\resources\\generatorConfig.xml";

        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        File configFile = new File(configPath);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);


        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);

        warnings.forEach((i) ->{
            System.out.println(i);
        });

    }
}
