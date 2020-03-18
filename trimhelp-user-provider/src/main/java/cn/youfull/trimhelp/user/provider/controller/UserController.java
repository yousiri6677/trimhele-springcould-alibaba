package cn.youfull.trimhelp.user.provider.controller;

import cn.youfull.trimhelp.entity.User;
import cn.youfull.trimhelp.user.provider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login/{account}/{passWord}",method = RequestMethod.GET)
    public User loginByAccountAndPassWord(@PathVariable("account") String account,@PathVariable("passWord") String passWord) {
        return userService.loginByAccountAndPassWord(account, passWord);
    }

    @RequestMapping(value = "/addUser/{user}",method = RequestMethod.GET)
    public int addUser(@PathVariable("user") User user) {
        return userService.addUser(user);
    }


}
