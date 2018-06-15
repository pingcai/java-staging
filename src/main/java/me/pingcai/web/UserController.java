package me.pingcai.web;

import lombok.extern.slf4j.Slf4j;
import me.pingcai.service.TestService;
import me.pingcai.vo.ResponseFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by pingcai at 2018/6/13 14:18
 */
@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private TestService testService;

    @RequestMapping(value = "alive",
            method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.DELETE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object alive(@RequestParam(defaultValue = "1") Long id) {
        log.info("当前登录用户：{}", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return ResponseFactory.buildSuccess(testService.selectByPrimaryKey(id));
    }

}
