package me.pingcai.web;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import me.pingcai.domain.entity.User;
import me.pingcai.domain.dto.HttpResponse;
import me.pingcai.domain.vo.UserVo;
import me.pingcai.enums.ReturnCode;
import me.pingcai.service.DomainService;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
    public Object create(@Validated @RequestBody UserVo user) {
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
                    UserVo res = new UserVo();
                    BeanUtils.copyProperties(user, res);
                    res.setPassword(null);
                    return HttpResponse.buildSuccess(res);
                })
                .orElse(HttpResponse.buildError(ReturnCode.USER_NOT_EXIST));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Object list(PageInfo<User> pageInfo) {
        return domainService.selectUserByPage(pageInfo);
    }

}
