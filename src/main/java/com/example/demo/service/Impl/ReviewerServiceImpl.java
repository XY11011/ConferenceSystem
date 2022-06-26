package com.example.demo.service.Impl;

import com.example.demo.mapper.ReviewerMapper;
import com.example.demo.pojo.Reviewer;
import com.example.demo.service.ReviewerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author 梦里花开又半夏~
 * @date 2022/5/1 16:18
 */
@Service
public class ReviewerServiceImpl implements ReviewerService {
    @Resource
    ReviewerMapper reviewerMapper;


    @Override
    public int getReviewerId(String username) {
        int reviewerId = reviewerMapper.queryReviewerByUsername(username).getId();
        return reviewerId;
    }
    @Override
    public Reviewer queryReviewerByUsername(String username) {
        Reviewer reviewer=reviewerMapper.queryReviewerByUsername(username);
        return reviewer;
    }

    @Override
    public int addReviewer(Reviewer reviewer) {
        int res= reviewerMapper.addReviewer(reviewer);
        return res;
    }

    @Override
    public int updateReviewerInfo(Reviewer reviewer) {
        int res=reviewerMapper.updateReviewerInfo(reviewer);
        return res;
    }

    @Override
    public int updateReviewerPwd(Reviewer reviewer) {
        int res=reviewerMapper.updateReviewerPwd(reviewer);
        return res;
    }
}
