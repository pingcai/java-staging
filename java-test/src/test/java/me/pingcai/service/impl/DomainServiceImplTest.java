package me.pingcai.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.pingcai.ApplicationContextTests;
import me.pingcai.dao.entity.User;
import me.pingcai.service.DomainService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author huangpingcai
 * @since 2018/9/26 11:56
 */
@Slf4j
public class DomainServiceImplTest extends ApplicationContextTests {

    @Autowired
    private DomainService domainService;

    @Test
    public void selectByPrimaryKey() {
        User user = domainService.selectUserByPrimaryKey(1011436L);
        log.info(user.getName());
    }
}