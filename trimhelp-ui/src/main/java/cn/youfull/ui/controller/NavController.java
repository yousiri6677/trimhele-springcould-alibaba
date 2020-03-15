package cn.youfull.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NavController {

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

}
