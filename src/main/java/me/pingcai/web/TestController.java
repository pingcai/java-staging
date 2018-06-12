package me.pingcai.web;

import lombok.extern.slf4j.Slf4j;
import me.pingcai.service.TestService;
import me.pingcai.vo.ResponseFactory;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

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
    public Object alive(@RequestParam(defaultValue = "1") Long id) {
        log.info("Request success !");
        return ResponseFactory.buildSuccess(testService.selectByPrimaryKey(id));
    }


    @RequestMapping(value = "user/test",
            method = {RequestMethod.POST},
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Object test(@RequestParam(value = "file") MultipartFile[] files, Long data) {
        log.info("Request Params : " + CollectionUtils.size(files));
        return ResponseFactory.buildSuccess(data);
    }
}
