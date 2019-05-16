package me.pingcai.reposiroty;

import lombok.extern.slf4j.Slf4j;
import me.pingcai.dao.entity.User;
import me.pingcai.dao.enums.UserSex;
import me.pingcai.dao.enums.UserStatus;
import me.pingcai.util.IpUtils;
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
                        user.setName(StringUtils.substring(UUID.randomUUID().toString(), 0, 31));
                        user.setAge(j % 127);
                        user.setBirthday(new Date());
                        user.setSex(random.nextBoolean() ? UserSex.MALE : UserSex.FEMALE);
                        user.setStatus(random.nextBoolean() ? UserStatus.NORMAL : UserStatus.NONACTIVATED);
                        user.setRegisterIp(j % 1000 == 0 ? IpUtils.ip2Long("127.0.0.1") : buildIp(j));
                        user.setComment(String.valueOf(j));
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

    @Test
    public void testIp() {
        System.out.println(IpUtils.ip2Long("127.0.0.1"));
    }

    private Long buildIp(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(i % 255 + 1)
                .append(".")
                .append(i % 127)
                .append(".")
                .append(i % 192)
                .append(".")
                .append(i % 182);
        return IpUtils.ip2Long(sb.toString());
    }
}
