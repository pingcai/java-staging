package me.pingcai.web;

import lombok.extern.slf4j.Slf4j;
import me.pingcai.dao.entity.User;
import me.pingcai.domain.HttpResponseFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * create by 黄平财 at 2017/11/30 22:52
 */
@Slf4j
@RestController
public class TestController {

    @RequestMapping(value = "alive", method = RequestMethod.GET)
    public Object alive() {
        log.info("request success");
        return HttpResponseFactory.buildSuccess();
    }

    @RequestMapping(value = "echo", method = RequestMethod.POST)
    public Object echo(@RequestBody User user) {
        log.info("echo success, user: {}", user);
        return HttpResponseFactory.buildSuccess(user);
    }

}
