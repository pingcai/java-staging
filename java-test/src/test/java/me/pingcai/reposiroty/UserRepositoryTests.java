package me.pingcai.reposiroty;

import lombok.extern.slf4j.Slf4j;
import me.pingcai.ApplicationContextTests;
import me.pingcai.dao.entity.User;
import me.pingcai.dao.enums.UserSex;
import me.pingcai.dao.enums.UserStatus;
import me.pingcai.reposiroty.UserRepository;
import me.pingcai.util.IpUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

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
public class UserRepositoryTests extends ApplicationContextTests {

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
                    for (int i1 = 0; i1 < 60000; i1++) {
                        User user = new User();
                        user.setName(StringUtils.substring(UUID.randomUUID().toString(), 0, 31));
                        user.setAge(i1 % 127);
                        user.setBirthday(new Date());
                        user.setSex(random.nextBoolean() ? UserSex.MALE : UserSex.FEMALE);
                        user.setStatus(random.nextBoolean() ? UserStatus.NORMAL : UserStatus.NONACTIVATED);
                        user.setRegisterIp(i1 % 1000 == 0 ? IpUtils.ip2Long("127.0.0.1") : buildIp(i1));
                        user.setComment(String.valueOf(i1));
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
