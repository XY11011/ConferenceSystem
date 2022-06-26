package com.example.demo.service;

import com.example.demo.pojo.User;

/**
 * @author 梦里花开又半夏~
 * @date 2022/5/1 16:17
 */
public interface UserService {
    //    通过用户名查找用户
    User queryUserByUsername(String username);

    //添加用户
    int addUser(User user);

    //更新用户的个人信息
    int updateUserInfo(User user);

    //    更新用户的密码
    int updateUserPwd(User user);
}
