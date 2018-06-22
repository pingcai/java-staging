package me.pingcai.service.impl;

import me.pingcai.dao.entity.User;
import me.pingcai.dao.mapper.UserMapper;
import me.pingcai.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by pingcai at 2018/6/13 13:55
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public User selectByNickName(String account) {
        return userMapper.selectByNickName(account);
    }
}
