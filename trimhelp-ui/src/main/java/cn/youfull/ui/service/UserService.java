package cn.youfull.ui.service;

import cn.youfull.trimhelp.entity.User;

public interface UserService {
    User loginByAccountAndPassWord(String account,String passWord);

    int addUser(User user);
}