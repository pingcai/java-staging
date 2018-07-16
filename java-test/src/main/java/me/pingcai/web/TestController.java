package me.pingcai.web;

import lombok.extern.slf4j.Slf4j;
import me.pingcai.enums.UserType;
import me.pingcai.vo.ResponseFactory;
import me.pingcai.vo.req.UserVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * create by 黄平财 at 2017/11/30 22:52
 */
@RestController
@Slf4j
public class TestController {

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


    @RequestMapping(value = "test",
            method = {RequestMethod.POST})
    public Object test(UserVo userVo) {
        log.info("UserType : " + userVo.getUserType());
        return ResponseFactory.buildSuccess(userVo);
    }
}
