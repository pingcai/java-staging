package me.pingcai.service;

import lombok.extern.slf4j.Slf4j;
import me.pingcai.domain.dto.SimplePageInfo;
import me.pingcai.domain.entity.User;
import me.pingcai.enums.ReturnCode;
import me.pingcai.exception.BizException;
import me.pingcai.reposiroty.UserRepository;
import me.pingcai.util.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DomainService {

    @Autowired
    private UserRepository userRepository;

    public User selectUserByPrimaryKey(Long id) {
        if (id == null || id.intValue() <= 0) {
            return null;
        }
        return userRepository.selectByPrimaryKey(id);
    }

    public User selectUserByName(String name) {
        return Optional.ofNullable(name).map(n -> userRepository.selectByName(n)).orElse(null);
    }

    public Long insertUserIfNotExist(User user) {
        User dbUser = userRepository.selectByName(user.getName());
        if (dbUser != null) {
            throw new BizException(ReturnCode.USER_EXIST);
        }
        userRepository.insert(user);
        return user.getId();
    }


    public SimplePageInfo<User> selectUserByPage(SimplePageInfo<User> pageInfo) {
        List<User> list = ListUtils.safeList(userRepository.selectUserByPage(pageInfo.getPageNum(), pageInfo.getPageSize()));
        return new SimplePageInfo<>(list);
    }
}
