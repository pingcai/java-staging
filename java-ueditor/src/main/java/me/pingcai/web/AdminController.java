package me.pingcai.web;

import me.pingcai.service.AdminService;
import me.pingcai.util.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/upload/image", method = RequestMethod.GET)
    public Object getConfig(String action) {
        if("config".equals(action)){
            return "forward:/static/ueditor/jsp/config.json";
        }
        return "index";
    }

    @RequestMapping(value = "/upload/image", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadImage(@RequestParam("upfile") MultipartFile uploadFile) {
        return JsonUtils.object2Json(adminService.uploadImage(uploadFile));
    }




}
