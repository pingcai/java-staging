package me.pingcai.web;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import me.pingcai.domain.dto.HttpResponse;
import me.pingcai.domain.dto.SimplePageInfo;
import me.pingcai.domain.entity.User;
import me.pingcai.domain.enums.UserStatus;
import me.pingcai.domain.vo.user.login.UserLoginReqVo;
import me.pingcai.domain.vo.user.UserResVo;
import me.pingcai.domain.vo.user.register.UserRegisterReqVo;
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
    public Object register(@Validated @RequestBody UserRegisterReqVo userRegisterReqVo) {
        User user = buildUser(userRegisterReqVo);
        Long id = domainService.insertUserIfNotExist(user);
        if (id > 0) {
            return HttpResponse.buildSuccess(Maps.immutableEntry("id", id));
        }
        return HttpResponse.buildError(ReturnCode.INTERNAL_ERROR);
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Object login(@Validated @RequestBody UserLoginReqVo loginVo) {
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

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Object get(@PathVariable("id") Long id) {
        return Optional.ofNullable(domainService.selectUserByPrimaryKey(id))
                .map(user -> {
                    UserResVo res = new UserResVo();
                    BeanUtils.copyProperties(user, res);
                    return HttpResponse.buildSuccess(res);
                })
                .orElse(HttpResponse.buildError(ReturnCode.USER_NOT_EXIST));
    }

    private User buildUser(UserRegisterReqVo userRegisterReqVo) {
        Date now = new Date();
        User user = new User();

        user.setStatus(UserStatus.NORMAL);
        user.setName(userRegisterReqVo.getName());
        user.setDisplayName(userRegisterReqVo.getDisplayName());
        user.setProfilePicture(userRegisterReqVo.getProfilePicture() == null ? StringUtils.EMPTY : userRegisterReqVo.getProfilePicture());
        user.setPhone(userRegisterReqVo.getPhone());
        user.setEmail(userRegisterReqVo.getEmail());

        Pair<String, String> saltPassword = PasswordHash.createHash(userRegisterReqVo.getPassword());
        user.setPasswordSalt(saltPassword.getKey());
        user.setPassword(saltPassword.getValue());

        user.setSex(userRegisterReqVo.getSex());
        user.setAge(userRegisterReqVo.getAge());
        user.setBirthday(userRegisterReqVo.getBirthday());
        user.setAddTime(now);
        user.setUpdateTime(now);
        return user;
    }

    private UserResVo buildUserLoginVo(User user) {
        UserResVo loginResVo = new UserResVo();
        loginResVo.setId(user.getId());
        loginResVo.setStatus(user.getStatus());
        loginResVo.setName(user.getName());
        loginResVo.setDisplayName(user.getDisplayName());
        loginResVo.setProfilePicture(user.getProfilePicture() == null ? StringUtils.EMPTY : user.getProfilePicture());
        loginResVo.setPhone(user.getPhone());
        loginResVo.setEmail(user.getEmail());
        loginResVo.setSex(user.getSex());
        loginResVo.setAge(user.getAge());
        loginResVo.setBirthday(user.getBirthday());
        loginResVo.setIntroduction(user.getIntroduction());
        loginResVo.setAddTime(user.getAddTime());
        loginResVo.setUpdateTime(user.getUpdateTime());
        return loginResVo;
    }
}
