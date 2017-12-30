package me.pingcai.web;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * create by 黄平财 at 2017/11/30 22:52
 */
@RestController
@Slf4j
public class TestController {

    @RequestMapping(value = "alive",
            method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.DELETE},
            produces = MediaType.TEXT_HTML_VALUE)
    public String alive(@RequestParam Map<String, Object> data) {
        log.info("Request success !");
        if (MapUtils.isNotEmpty(data)) {
            StringBuilder sb = new StringBuilder();
            data.forEach((key, val) -> {
                sb.append(key).append("=").append(val).append("&");
            });
            log.info("Request params : {}", sb.subSequence(0, sb.length() - 1));
        }
        return "yes";
    }


    @RequestMapping(value = "json",
            method = {RequestMethod.POST, RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.DELETE},
            produces = MediaType.TEXT_HTML_VALUE)
    public String json(@RequestBody Map<String, Object> data) {
        log.info("Request success !");
        if (MapUtils.isNotEmpty(data)) {
            StringBuilder sb = new StringBuilder();
            data.forEach((key, val) -> {
                sb.append(key).append("=").append(val).append("&");
            });
            log.info("Request params : {}", sb.subSequence(0, sb.length() - 1));
        }
        return "yes";
    }

}
