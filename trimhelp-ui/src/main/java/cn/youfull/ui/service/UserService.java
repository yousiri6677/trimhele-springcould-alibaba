package cn.youfull.ui.service;


import cn.youfull.trimhelp.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "trimhelp-user-provider")
public interface UserService {

    @RequestMapping("/login")
    User loginByAccountAndPassWord(String account, String passWord);

    @RequestMapping("/addUser")
    int addUser(User user);
}
