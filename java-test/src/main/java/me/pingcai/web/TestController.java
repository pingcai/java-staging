package me.pingcai.web;

import lombok.extern.slf4j.Slf4j;
import me.pingcai.dao.entity.Test;
import me.pingcai.service.TestService;
import me.pingcai.vo.ResponseFactory;
import org.apache.commons.collections4.MapUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * create by 黄平财 at 2017/11/30 22:52
 */
@RestController
@Slf4j
public class TestController {

    @Resource
    private TestService testService;

    @RequestMapping(value = "alive",
            method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.DELETE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Object alive(Map<String, Object> data) {
        log.info("Request success !");
        if (MapUtils.isNotEmpty(data)) {
            StringBuilder sb = new StringBuilder();
            data.forEach((key, val) -> {
                sb.append(key).append("=").append(val).append("&");
            });
            log.info("Request params : {}", sb.subSequence(0, sb.length() - 1));
        }
        return ResponseFactory.buildSuccess(data);
    }


    @RequestMapping(value = "json",
            method = {RequestMethod.POST, RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.DELETE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Object json(@RequestBody Map<String, Object> data) {
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


    @RequestMapping(value = "insert",
            method = {RequestMethod.POST},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Object insert(@RequestBody Map<String, Object> data) {

        Test test = new Test();
        test.setName((String) data.get("name"));
        test.setAge(Byte.parseByte(String.valueOf(data.getOrDefault("age",0))));
        test.setId(Long.parseLong(String.valueOf(data.getOrDefault("id",0L))));

        data.clear();

        data.put("id",testService.insert(test));

        return data;
    }
}
