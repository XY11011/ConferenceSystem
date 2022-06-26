package com.example.demo.service;

import com.example.demo.pojo.Conference;
import com.example.demo.pojo.Reviewer;

import java.util.List;

/**
 * @author Accelerator
 * @date 2022/5/1 22:47
 */
public interface ReviewerManageService {

    // 搜索框搜索用户名查找审稿人信息
    List<Reviewer> queryReviewerBySearchUsername(String searchString);

    // 添加审稿人信息
    boolean createReviewer(Reviewer reviewer);

    // 根据审稿人ID查询审稿人信息
    Reviewer queryReviewerById(int id);

}
