package com.youfull.trimhelp.ui.service;


import com.youfull.trimhelp.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "trimhelp-user-provider")
public interface UserService {

    @GetMapping("/login/{account}/{passWord}")
    User loginByAccountAndPassWord(@PathVariable(value = "account")String account, @PathVariable(value = "passWord") String passWord);

    @GetMapping("/addUser/{user}")
    int addUser(@PathVariable(value = "user")User user);
}
