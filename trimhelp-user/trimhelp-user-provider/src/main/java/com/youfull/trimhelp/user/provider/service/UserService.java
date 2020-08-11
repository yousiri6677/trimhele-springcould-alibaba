package com.youfull.trimhelp.user.provider.service;

import com.youfull.trimhelp.entity.User;


public interface UserService {

    User loginByAccountAndPassWord(String account, String passWord);

    int addUser(User user);
}
