package me.pingcai.web;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import me.pingcai.dao.entity.User;
import me.pingcai.enums.ReturnCode;
import me.pingcai.service.DomainService;
import me.pingcai.util.IpUtils;
import me.pingcai.vo.HttpResponseFactory;
import me.pingcai.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
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
    private DomainService domainService;

    @RequestMapping(method = RequestMethod.POST)
    public Object insert(@Validated UserVo user, HttpServletRequest request) {
        user.setRegisterIp(IpUtils.getIp(request));
        Long id = domainService.insertUserIfNotExist(user);
        if(id > 0){
            Map<String,Long> data = Maps.newHashMap();
            data.put("id",id);
            log.info("add user success !");
            return HttpResponseFactory.buildSuccess(data);
        }
        return HttpResponseFactory.build(ReturnCode.FAIL);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Object get(@PathVariable("id") Long id) {
        User user = domainService.selectUserByPrimaryKey(id);
        UserVo res = null;
        if(Objects.nonNull(user)){
            res = new UserVo();
            BeanUtils.copyProperties(user,res);
            res.setRegisterIp(IpUtils.long2Ip(user.getRegisterIp()));
        }
        log.info("打日志");
        return Objects.isNull(res) ? HttpResponseFactory.build(ReturnCode.USER_NOT_EXIST) : HttpResponseFactory.buildSuccess(res);
    }


}
