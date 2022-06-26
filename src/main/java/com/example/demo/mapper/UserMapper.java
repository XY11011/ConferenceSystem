package com.example.demo.mapper;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 梦里花开又半夏~
 * @date 2022/5/1 16:13
 */
@Repository
@Mapper
public interface UserMapper {

    // 通过（用户名）查找用户
    User queryUserByUsername(String username);

    //添加用户
    int addUser(User user);

    //更新用户的个人信息
    int updateUserInfo(User user);

    //    更新用户的密码
    int updateUserPwd(User user);

}
