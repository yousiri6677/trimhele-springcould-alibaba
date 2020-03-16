package cn.youfull.ui.controller;

import cn.youfull.trimhelp.entity.User;
import cn.youfull.ui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NavController {

    @Autowired
    public UserService userService;

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(String account,String passWord){
        System.out.println(1);
        User user = userService.loginByAccountAndPassWord(account, passWord);
        if (user!=null){
             System.out.println(2);
             return "index";
         } else {
             return "login";
         }
    }


    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/toRegister")
    public String toregister(){
        return "register";
    }


    @RequestMapping("/register")
    public String register(User user){
        int i = userService.addUser(user);
        if (i>0){
            return "login";
        }else {
            return "register";
        }
    }



    @RequestMapping("/toRelease")
    public String torelease(){
        return "release";
    }


    @RequestMapping("/toProjectHall")
    public String toprojectHall(){
        return "projectHall";
    }




}
