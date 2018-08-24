package me.pingcai.web;

import lombok.extern.slf4j.Slf4j;
import me.pingcai.dao.entity.User;
import me.pingcai.enums.HttpError;
import me.pingcai.service.UserService;
import me.pingcai.vo.ResponseFactory;
import me.pingcai.vo.UserVo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
    public Object insert(UserVo user) {
        Long id = userService.insertIfNotExist(user);
        return ResponseFactory.buildSuccess(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Object get(@PathVariable("id") Long id) {
        User res = userService.selectByPrimaryKey(id);
        return Objects.isNull(res) ? ResponseFactory.build(HttpError.NOT_EXIST) : ResponseFactory.buildSuccess(res);
    }


}
