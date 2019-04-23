package me.pingcai.web;

import lombok.extern.slf4j.Slf4j;
import me.pingcai.vo.HttpResponseFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * create by 黄平财 at 2017/11/30 22:52
 */
@RestController
@Slf4j
public class TestController {

    @RequestMapping(value = "alive")
    public Object alive() {
        log.info("request success");
        return HttpResponseFactory.buildSuccess("ok");
    }

}
