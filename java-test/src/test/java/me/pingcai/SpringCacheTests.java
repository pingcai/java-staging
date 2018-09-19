package me.pingcai;

import lombok.extern.slf4j.Slf4j;
import me.pingcai.dao.entity.User;
import me.pingcai.service.UserService;
import me.pingcai.util.JsonUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author huangpingcai
 * @since 2018/9/19 18:20
 */
@Slf4j
public class SpringCacheTests extends ApplicationContextTests {
    @Autowired
    private UserService userService;

    @Test
    public void testCache() {

        User dbUser = userService.selectByPrimaryKey(1011429L);

        log.info("user from db: {}", JsonUtils.object2Json(dbUser));

        User cacheUser = userService.selectByPrimaryKey(1011429L);

        log.info("user from cache: {}", JsonUtils.object2Json(cacheUser));

        dbUser = userService.selectByPrimaryKey(1011436L);

        log.info("user from db: {}", JsonUtils.object2Json(dbUser));

    }
}
