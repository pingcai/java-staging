package me.pingcai.web;

import lombok.extern.slf4j.Slf4j;
import me.pingcai.domain.enums.UserSex;
import me.pingcai.domain.vo.user.register.UserRegisterReqVo;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @author huangpingcai
 * @since 2019-05-25 12:23
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private UserController userController;

    @Test
    public void register() {

        Random random = new Random();

        for (int j = 0; j < 1000; j++) {
            UserRegisterReqVo user = new UserRegisterReqVo();
            user.setName(StringUtils.substring(UUID.randomUUID().toString(), 0, 32));
            user.setDisplayName("钢铁侠");
            user.setPhone("1312515123");
            user.setEmail("hpingcai@gmail.com");
            user.setPassword("123");
            user.setSex(random.nextBoolean() ? UserSex.MALE : UserSex.FEMALE);
            user.setAge(j % 127);
            user.setBirthday(new Date());
            user.setIntroduction(String.valueOf(j));
            user.setIntroduction("个人简介");
            userController.register(user);
        }
    }
}