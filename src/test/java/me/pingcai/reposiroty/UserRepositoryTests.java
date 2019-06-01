package me.pingcai.reposiroty;

import lombok.extern.slf4j.Slf4j;
import me.pingcai.domain.entity.User;
import me.pingcai.domain.enums.UserSex;
import me.pingcai.domain.enums.UserStatus;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * @author huangpingcai
 * @since 2018/9/2 21:54
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

    @Resource
    private UserRepository userRepository;

    @Test
    public void test() throws InterruptedException {
        Random random = new Random();
        int count = 20;
        CountDownLatch latch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 60000; j++) {
                        User user = new User();
                        user.setStatus(random.nextBoolean() ? UserStatus.NORMAL:UserStatus.DELETED);
                        user.setName(StringUtils.substring(UUID.randomUUID().toString(), 0, 31));
                        user.setDisplayName("钢铁侠");
                        user.setPhone("1312515123");
                        user.setEmail("hpingcai@gmail.com");
                        user.setPassword("123");
                        user.setSex(random.nextBoolean() ? UserSex.MALE : UserSex.FEMALE);
                        user.setAge(j % 127);
                        user.setBirthday(new Date());
                        user.setIntroduction(String.valueOf(j));
                        user.setIntroduction("个人简介");
                        Date now  = new Date();
                        user.setAddTime(now);
                        user.setUpdateTime(now);
                        userRepository.insert(user);
                    }
                } catch (Exception e) {
                    log.error("error : ", e);
                } finally {
                    latch.countDown();
                }

            }).start();
        }
        latch.await();
    }
}
