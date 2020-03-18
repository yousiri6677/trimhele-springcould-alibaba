package cn.youfull.trimhelp.user.provider.controller;

import cn.youfull.trimhelp.entity.User;
import cn.youfull.trimhelp.user.provider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public User loginByAccountAndPassWord(String account, String passWord) {
        return userService.loginByAccountAndPassWord(account, passWord);
    }

    @RequestMapping(value = "/addUser",method = RequestMethod.GET)
    public int addUser(User user) {
        return userService.addUser(user);
    }


}
