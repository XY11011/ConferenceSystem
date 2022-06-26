package com.example.demo.service;

import com.example.demo.pojo.Reviewer;
import com.example.demo.pojo.User;

import javax.servlet.http.HttpSession;

/**
 * @author 梦里花开又半夏~
 * @date 2022/5/1 16:17
 */
public interface ReviewerService {
    //返回当前评审人员的id
    int getReviewerId(String username);
    //    通过用户名查找评审人
    Reviewer queryReviewerByUsername(String username);

    //添加审稿人
    int addReviewer(Reviewer reviewer);

    //更新审稿人的个人信息
    int updateReviewerInfo(Reviewer reviewer);

    //    更新用户的密码
    int updateReviewerPwd(Reviewer reviewer);

}
