package me.pingcai.service.impl;

import me.pingcai.dao.entity.User;
import me.pingcai.enums.HttpError;
import me.pingcai.exception.ApiException;
import me.pingcai.reposiroty.UserRepository;
import me.pingcai.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public User insertIfNotExist(User user){
        if(!check(user)){
            throw ApiException.create(HttpError.INVALID_PARAM);
        }

        User dbUser = userRepository.selectByName(user);
        if(dbUser != null){
            throw ApiException.create(HttpError.EXIST);
        }

        userRepository.insert(user);
        return user;
    }

    private boolean check(User user) {
        return user != null && user.getName() != null;
    }


}
