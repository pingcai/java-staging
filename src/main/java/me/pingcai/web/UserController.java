package me.pingcai.web;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import me.pingcai.domain.dto.HttpResponse;
import me.pingcai.domain.dto.SimplePageInfo;
import me.pingcai.domain.entity.User;
import me.pingcai.domain.enums.UserStatus;
import me.pingcai.domain.vo.UserLoginVo;
import me.pingcai.domain.vo.UserRegisterVo;
import me.pingcai.enums.ReturnCode;
import me.pingcai.service.DomainService;
import me.pingcai.util.PasswordHash;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;

/**
 * @author huangpingcai
 * @since 2018/8/24 23:39
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private DomainService domainService;

    @RequestMapping(method = RequestMethod.POST)
    public Object create(@Validated @RequestBody UserRegisterVo userRegisterVo) {
        User user = buildUser(userRegisterVo);
        Long id = domainService.insertUserIfNotExist(user);
        if (id > 0) {
            return HttpResponse.buildSuccess(Maps.immutableEntry("id", id));
        }
        return HttpResponse.buildError(ReturnCode.INTERNAL_ERROR);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Object get(@PathVariable("id") Long id) {
        return Optional.ofNullable(domainService.selectUserByPrimaryKey(id))
                .map(user -> {
                    UserRegisterVo res = new UserRegisterVo();
                    BeanUtils.copyProperties(user, res);
                    res.setPassword(null);
                    return HttpResponse.buildSuccess(res);
                })
                .orElse(HttpResponse.buildError(ReturnCode.USER_NOT_EXIST));
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Object login(@Validated @RequestBody UserLoginVo loginVo) {
        User user = domainService.selectUserByName(loginVo.getName());
        if (user == null) {
            return HttpResponse.buildError(ReturnCode.NAME_PASSWORD_MISMATCH);
        }
        boolean valid = PasswordHash.validatePassword(loginVo.getPassword(), user.getPasswordSalt(), user.getPassword());
        if (!valid) {
            return HttpResponse.buildError(ReturnCode.NAME_PASSWORD_MISMATCH);
        }
        return HttpResponse.buildSuccess(buildUserLoginVo(user));
    }

    @RequestMapping(value = "forgetPassword", method = RequestMethod.POST)
    public Object forgetPassword() {
        return null;
    }

    @RequestMapping(value = "changePassword", method = RequestMethod.POST)
    public Object changePassword() {
        return null;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Object list(SimplePageInfo<User> pageInfo) {
        return domainService.selectUserByPage(pageInfo);
    }

    private User buildUser(UserRegisterVo userRegisterVo) {
        Date now = new Date();
        User user = new User();

        user.setStatus(UserStatus.NORMAL);
        user.setName(userRegisterVo.getName());
        user.setDisplayName(userRegisterVo.getDisplayName());
        user.setProfilePicture(userRegisterVo.getProfilePicture() == null ? StringUtils.EMPTY : userRegisterVo.getProfilePicture());
        user.setPhone(userRegisterVo.getPhone());
        user.setEmail(userRegisterVo.getEmail());

        Pair<String, String> saltPassword = PasswordHash.createHash(userRegisterVo.getPassword());
        user.setPasswordSalt(saltPassword.getKey());
        user.setPassword(saltPassword.getValue());

        user.setSex(userRegisterVo.getSex());
        user.setAge(userRegisterVo.getAge());
        user.setBirthday(userRegisterVo.getBirthday());
        user.setAddTime(now);
        user.setUpdateTime(now);
        return user;
    }

    private UserLoginVo buildUserLoginVo(User user) {
        UserLoginVo loginVo = new UserLoginVo();
        loginVo.setStatus(user.getStatus());
        loginVo.setName(user.getName());
        loginVo.setDisplayName(user.getDisplayName());
        loginVo.setProfilePicture(user.getProfilePicture() == null ? StringUtils.EMPTY : user.getProfilePicture());
        loginVo.setPhone(user.getPhone());
        loginVo.setEmail(user.getEmail());
        loginVo.setPassword(null);
        loginVo.setSex(user.getSex());
        loginVo.setAge(user.getAge());
        loginVo.setBirthday(user.getBirthday());
        loginVo.setIntroduction(user.getIntroduction());
        loginVo.setAddTime(user.getAddTime());
        loginVo.setUpdateTime(user.getUpdateTime());
        return loginVo;
    }
}
