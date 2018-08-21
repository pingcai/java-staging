package me.pingcai;

import lombok.extern.slf4j.Slf4j;
import me.pingcai.es.document.User;
import me.pingcai.es.repositories.EsUserRepository;
import me.pingcai.util.JsonUtils;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
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
            log.info("result : {}", JsonUtils.object2Json(user));
        });
    }


    @Test
    public void testAddUser() {
        User user = new User();
        user.setId(12L);
        user.setName("杰克");
        user.setAge((byte) 12);
        user.setSex(((byte) 1));
        user.setStatus((byte) 1);
        user.setComment("备注:中国(中华人民共和国)是世界上最大的社会主义国家");
        user.setBirthday(new Date());

        User result = esUserRepository.index(user);

        log.info("result : {}", JsonUtils.object2Json(result));
    }

    @Test
    public void testSearch() {

        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("comment", "中国");

        Iterable<User> it = esUserRepository.search(queryBuilder);

        it.forEach(user -> {
            log.info("match user : {}", JsonUtils.object2Json(user));
        });

    }

}
