package me.pingcai.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.pingcai.dao.entity.Order;
import me.pingcai.dao.entity.User;
import me.pingcai.enums.HttpError;
import me.pingcai.exception.ApiException;
import me.pingcai.reposiroty.OrderRepository;
import me.pingcai.reposiroty.UserRepository;
import me.pingcai.service.DomainService;
import me.pingcai.util.IpUtils;
import me.pingcai.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;

@Slf4j
@Service
public class DomainServiceImpl implements DomainService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

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
            throw ApiException.newInstance(HttpError.EXIST);
        }
        dbUser = new User();
        BeanUtils.copyProperties(user,dbUser);
        dbUser.setRegisterIp(IpUtils.ip2Long(user.getRegisterIp()));
        userRepository.insert(dbUser);
        return dbUser.getId();
    }

    @Override
    @Transactional
    public boolean deleteOrderAndUpdateUser(Order order, User user) {
        if(Objects.isNull(order) || Objects.isNull(user)){
            return false;
        }
        int deleted = orderRepository.deleteByPrimaryKey(order.getId());
        int updated = 0;
        try {
            updated = userRepository.freezeUser(user);
        } catch (Exception e) {
            userRepository.deleteByPrimaryKey(user.getId());
            log.error("freeze user error, delete this user,  useId: {}",user.getId(),e);
        }
        return updated == 1 && deleted == 1;
    }


}
