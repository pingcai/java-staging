package me.pingcai.service.impl;

import me.pingcai.dao.entity.User;
import me.pingcai.enums.ReturnCode;
import me.pingcai.exception.ApiException;
import me.pingcai.reposiroty.UserRepository;
import me.pingcai.service.DomainService;
import me.pingcai.util.IpUtils;
import me.pingcai.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DomainServiceImpl implements DomainService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User selectUserByPrimaryKey(Long id) {
        if(id == null || id.intValue() <= 0){
            return null;
        }
        return userRepository.selectByPrimaryKey(id);
    }

    @Override
    public Long insertUserIfNotExist(UserVo user){
        User dbUser = userRepository.selectByName(user.getName());
        if(dbUser != null){
            throw ApiException.newInstance(ReturnCode.USER_EXIST);
        }
        dbUser = new User();
        BeanUtils.copyProperties(user,dbUser);
        dbUser.setRegisterIp(IpUtils.ip2Long(user.getRegisterIp()));
        userRepository.insert(dbUser);
        return dbUser.getId();
    }



}
