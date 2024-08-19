package com.rcszh.lowcode.account.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rcszh.lowcode.account.entity.User;
import com.rcszh.lowcode.account.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public User findUserByUserName(String userName) {
        return userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserName, userName));
    }
}
