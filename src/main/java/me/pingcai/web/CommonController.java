package me.pingcai.web;

import me.pingcai.vo.ResponseFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by pingcai at 2018/6/13 14:18
 */
@Controller
public class CommonController {

    @ResponseBody
    @RequestMapping(value = "/login.htmlx",method = RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object login() {
        return ResponseFactory.buildSuccess("登录页面");
    }


}
