package com.example.demo.mapper;

import com.example.demo.pojo.Reviewer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ReviewerMapper {
//    Reviewer queryReviewerByUsername(String username);
    // 通过（用户名）查找审稿人
    Reviewer queryReviewerByUsername(String username);

    //添加审稿人
    int addReviewer(Reviewer reviewer);

    //更新审稿人的个人信息
    int updateReviewerInfo(Reviewer reviewer);

    //    更新用户的密码
    int updateReviewerPwd(Reviewer reviewer);
}
