package me.pingcai.service;

import com.github.pagehelper.PageInfo;
import me.pingcai.dao.entity.User;
import me.pingcai.domain.UserVo;
import me.pingcai.domain.dto.SimplePageInfo;
import me.pingcai.enums.ReturnCode;
import me.pingcai.exception.ApiException;
import me.pingcai.reposiroty.UserRepository;
import me.pingcai.util.IpUtils;
import me.pingcai.util.Lister;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Long insertUserIfNotExist(UserVo user) {
        User dbUser = userRepository.selectByName(user.getName());
        if (dbUser != null) {
            throw ApiException.newInstance(ReturnCode.USER_EXIST);
        }
        dbUser = new User();
        BeanUtils.copyProperties(user, dbUser);
        dbUser.setRegisterIp(IpUtils.ip2Long(user.getRegisterIp()));
        userRepository.insert(dbUser);
        return dbUser.getId();
    }


    public SimplePageInfo<User> selectUserByPage(PageInfo<User> pageInfo) {
        List<User> list = Lister.safeList(userRepository.selectUserByPage(pageInfo.getPageNum(), pageInfo.getPageSize()));
        return new SimplePageInfo<>(list);
    }
}
