package com.example.demo.service.Impl;

import com.example.demo.mapper.ConferenceManageMapper;
import com.example.demo.mapper.ReviewerManageMapper;
import com.example.demo.pojo.Reviewer;
import com.example.demo.service.ReviewerManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Accelerator
 * @date 2022/5/2 21:11
 */
@Service
public class ReviewerManageServiceImpl implements ReviewerManageService {
    @Resource
    ReviewerManageMapper reviewerManageMapper;

    @Override
    public List<Reviewer> queryReviewerBySearchUsername(String searchString) {
        return reviewerManageMapper.queryReviewerBySearchUsername(searchString);
    }

    @Override
    public boolean createReviewer(Reviewer reviewer) {
        return reviewerManageMapper.createReviewer(reviewer);
    }

    @Override
    public Reviewer queryReviewerById(int id) {
        return reviewerManageMapper.queryReviewerById(id);
    }
}
