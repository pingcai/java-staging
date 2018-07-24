package me.pingcai.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @RequestMapping("index")
    public ModelAndView index(ModelAndView view){
        view.addObject("name","velocity");
        view.setViewName("index");
        return view;
    }


}
