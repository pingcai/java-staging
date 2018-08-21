package me.pingcai;

import lombok.extern.slf4j.Slf4j;
import me.pingcai.es.document.User;
import me.pingcai.es.repositories.EsUserRepository;
import me.pingcai.util.JsonUtils;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author huangpingcai
 * @since 2018/8/21 11:35
 */
@Slf4j
@ContextConfiguration("classpath:spring/spring-es-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringElasticTemplateTests {

    @Autowired
    private EsUserRepository esUserRepository;

    @Autowired
    private Client client;

    @Test
    public void testFindUser() {
        Optional<User> optional = esUserRepository.findById(1L);
        optional.ifPresent(user -> {
            log.info("result : {}",JsonUtils.object2Json(user));
        });
    }



    @Test
    public void test() {



    }

}
