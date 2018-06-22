package me.pingcai.service.impl;

import me.pingcai.dao.entity.Test;
import me.pingcai.dao.mapper.TestMapper;
import me.pingcai.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by pingcai at 2018/6/7 17:53
 */
@Service
public class TestServiceImpl implements TestService {

    @Resource
    private TestMapper testMapper;

    @Override
    public Test selectByPrimaryKey(Long id) {
        return testMapper.selectByPrimaryKey(id);
    }
}
