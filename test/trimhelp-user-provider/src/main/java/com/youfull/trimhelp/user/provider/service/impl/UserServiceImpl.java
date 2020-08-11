package com.youfull.trimhelp.user.provider.service.impl;

import com.youfull.trimhelp.entity.User;
import com.youfull.trimhelp.mapper.UserMapper;
import com.youfull.trimhelp.user.provider.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author you17
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User loginByAccountAndPassWord(String account, String passWord) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("account",account);
        qw.eq("passWord",passWord);
        User user = userMapper.selectOne(qw);
        return user;
    }

    @Override
    public int addUser(User user) {
        int count = userMapper.insert(user);
        return count;
    }

}
