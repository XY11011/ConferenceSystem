package com.example.demo.service.Impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 梦里花开又半夏~
 * @date 2022/5/1 16:18
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public User queryUserByUsername(String username) {
        User user = userMapper.queryUserByUsername(username);
        return user;
    }
    @Override
    public int addUser(User user) {
        int res= userMapper.addUser(user);
        return res;
    }

    @Override
    public int updateUserInfo(User user) {
        int res=userMapper.updateUserInfo(user);
        return res;
    }

    @Override
    public int updateUserPwd(User user) {
        int res=userMapper.updateUserPwd(user);
        return res;
    }
}
