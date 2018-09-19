package me.pingcai.service.impl;

import me.pingcai.dao.entity.User;
import me.pingcai.enums.HttpError;
import me.pingcai.exception.ApiException;
import me.pingcai.reposiroty.UserRepository;
import me.pingcai.service.UserService;
import me.pingcai.util.IpUtils;
import me.pingcai.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Service
@CacheConfig(cacheNames = {"userCache"})
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Cacheable
    @Override
    public User selectByPrimaryKey(Long id) {
        if(id == null || id.intValue() <= 0){
            return null;
        }
        return userRepository.selectByPrimaryKey(id);
    }

    @Override
    public Long insertIfNotExist(UserVo user){
        User dbUser = userRepository.selectByName(user.getName());
        if(dbUser != null){
            throw ApiException.newInstance(HttpError.EXIST);
        }
        dbUser = new User();
        BeanUtils.copyProperties(user,dbUser);
        dbUser.setRegisterIp(IpUtils.ip2Long(user.getRegisterIp()));
        userRepository.insert(dbUser);
        return dbUser.getId();
    }

}
