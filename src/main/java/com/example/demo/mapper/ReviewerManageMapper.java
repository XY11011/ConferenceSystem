package com.example.demo.mapper;

import com.example.demo.pojo.Announcer;
import com.example.demo.pojo.Conference;
import com.example.demo.pojo.Reviewer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Accelerator
 * @date 2022/5/1 22:47
 */
@Repository
@Mapper
public interface ReviewerManageMapper {

    // 搜索框搜索用户名查找审稿人信息
    List<Reviewer> queryReviewerBySearchUsername(String searchString);

    // 添加审稿人信息
    boolean createReviewer(Reviewer reviewer);

    // 根据审稿人ID查询审稿人信息
    Reviewer queryReviewerById(int id);

}
