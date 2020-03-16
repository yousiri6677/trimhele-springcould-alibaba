package cn.youfull.ui.service;

import cn.youfull.trimhelp.entity.User;
import cn.youfull.ui.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User loginByAccountAndPassWord(String account,String passWord) {
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
