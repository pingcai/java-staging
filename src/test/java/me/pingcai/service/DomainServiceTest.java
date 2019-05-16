package me.pingcai.service;

import lombok.extern.slf4j.Slf4j;
import me.pingcai.dao.entity.User;
import me.pingcai.reposiroty.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * @author huangpingcai
 * @since 2018/9/26 11:56
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DomainServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    @Autowired
    private DomainService domainService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        User user = new User();
        user.setId(123L);
        user.setName("abc");
        when(userRepository.selectByPrimaryKey(anyLong())).thenReturn(user);
    }

    @Test
    public void selectByPrimaryKey() {
        User user = domainService.selectUserByPrimaryKey(1011436L);
        log.info(user.getName());
    }
}