package cn.youfull.trimhelp.ui.service;


import cn.youfull.trimhelp.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "trimhelp-user-provider")
public interface UserService {

    @RequestMapping("/login")
    User loginByAccountAndPassWord(@RequestParam(value = "account")String account,@RequestParam(value = "passWord") String passWord);

    @RequestMapping("/addUser")
    int addUser(@RequestParam(value = "user")User user);
}
