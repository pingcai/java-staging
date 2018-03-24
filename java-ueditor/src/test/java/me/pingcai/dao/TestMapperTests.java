package me.pingcai.dao;

import lombok.extern.slf4j.Slf4j;
import me.pingcai.dao.mapper.TestMapper;
import me.pingcai.util.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * create by 黄平财 at 2018/1/7 13:30
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-test.xml")
public class TestMapperTests {

    @Resource
    private TestMapper testMapper;

    @Test
    public void testSelect() {
        me.pingcai.dao.entity.Test test = testMapper.selectByPrimaryKey(1L);

        log.info("test : {}", JsonUtils.object2Json(test));

    }


}
