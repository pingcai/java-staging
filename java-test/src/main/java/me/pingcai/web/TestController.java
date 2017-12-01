package me.pingcai.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * create by 黄平财 at 2017/11/30 22:52
 */
@RestController
@Slf4j
public class TestController {
    @RequestMapping(value = "alive", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String alive() {


        log.info("Request success !");

        return "yes";
    }
}
