package me.pingcai.service.impl;

import me.pingcai.dao.entity.Test;
import me.pingcai.dao.mapper.TestMapper;
import me.pingcai.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * create by 黄平财 at 2018/1/7 12:55
 */
@Service
public class TestServiceImpl implements TestService{

    @Resource
    private TestMapper testMapper;

    @Override
    public int insert(Test test) {
        return testMapper.insert(test);
    }
}
