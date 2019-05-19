package me.pingcai.service;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import me.pingcai.domain.dto.SimplePageInfo;
import me.pingcai.domain.entity.User;
import me.pingcai.domain.enums.UserStatus;
import me.pingcai.domain.vo.UserVo;
import me.pingcai.enums.ReturnCode;
import me.pingcai.exception.ApiException;
import me.pingcai.reposiroty.UserRepository;
import me.pingcai.util.ListUtils;
import me.pingcai.util.PasswordHash;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    public Long insertUserIfNotExist(UserVo userVo) {
        User dbUser = userRepository.selectByName(userVo.getName());
        if (dbUser != null) {
            throw ApiException.newInstance(ReturnCode.USER_EXIST);
        }
        dbUser = buildUser(userVo);
        userRepository.insert(dbUser);
        return dbUser.getId();
    }

    private User buildUser(UserVo userVo) {

        Date now = new Date();

        User user = new User();
        user.setStatus(UserStatus.NORMAL);
        user.setName(userVo.getName());
        user.setDisplayName(userVo.getDisplayName());
        user.setProfilePicture(userVo.getProfilePicture() == null ? StringUtils.EMPTY : userVo.getProfilePicture());
        user.setPhone(userVo.getPhone());
        user.setEmail(userVo.getEmail());

        Pair<String, String> saltPassword = null;
        try {
            saltPassword = PasswordHash.createHash(userVo.getPassword());
        } catch (Exception e) {
            log.error("创建用户密码异常, name: {}, displayName: {}", userVo.getName(), user.getDisplayName(), e);
            throw ApiException.newInstance(ReturnCode.INTERNAL_ERROR);
        }
        user.setPasswordSalt(saltPassword.getKey());
        user.setPassword(saltPassword.getValue());
        user.setSex(userVo.getSex());
        user.setAge(userVo.getAge());
        user.setBirthday(userVo.getBirthday());
        user.setAddTime(now);
        user.setUpdateTime(now);
        return user;
    }


    public SimplePageInfo<User> selectUserByPage(PageInfo<User> pageInfo) {
        List<User> list = ListUtils.safeList(userRepository.selectUserByPage(pageInfo.getPageNum(), pageInfo.getPageSize()));
        return new SimplePageInfo<>(list);
    }
}
