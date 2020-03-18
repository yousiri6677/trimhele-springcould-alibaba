package cn.youfull.trimhelp.ui.service;


import cn.youfull.trimhelp.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "trimhelp-user-provider")
public interface UserService {

    @GetMapping("/login/{account}/{passWord}")
    User loginByAccountAndPassWord(@PathVariable(value = "account")String account, @PathVariable(value = "passWord") String passWord);

    @GetMapping("/addUser/{user}")
    int addUser(@PathVariable(value = "user")User user);
}
