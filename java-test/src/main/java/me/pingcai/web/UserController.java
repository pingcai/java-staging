package me.pingcai.web;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import me.pingcai.dao.entity.User;
import me.pingcai.enums.HttpError;
import me.pingcai.service.UserService;
import me.pingcai.util.IpUtils;
import me.pingcai.vo.ResponseFactory;
import me.pingcai.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * @author huangpingcai
 * @since 2018/8/24 23:39
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public Object insert(UserVo user, HttpServletRequest request) {
        user.setRegisterIp(IpUtils.getIp(request));
        Long id = userService.insertIfNotExist(user);
        Map<String,Long> data = Maps.newHashMap();
        data.put("id",id);
        return ResponseFactory.buildSuccess(data);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Object get(@PathVariable("id") Long id) {
        User user = userService.selectByPrimaryKey(id);
        UserVo res = null;
        if(Objects.nonNull(user)){
            res = new UserVo();
            BeanUtils.copyProperties(user,res);
            res.setRegisterIp(IpUtils.long2Ip(user.getRegisterIp()));
        }
        return Objects.isNull(res) ? ResponseFactory.build(HttpError.NOT_EXIST) : ResponseFactory.buildSuccess(res);
    }


}
