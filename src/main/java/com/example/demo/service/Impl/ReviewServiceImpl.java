package com.example.demo.service.Impl;

import com.example.demo.mapper.ReviewMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.ReviewService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author 梦里花开又半夏~
 * @date 2022/5/1 16:18
 */
@Service
public class ReviewServiceImpl implements ReviewService {
    @Resource
    ReviewMapper reviewMapper;

    @Override
    public List<Map<String,Object>> getReviewedPaper(Integer reviewer_id) {
        List<Map<String,Object>> reviewedPaper = reviewMapper.getReviewedPaper(reviewer_id);
        return reviewedPaper;
    }

    @Override
    public List<Map<String,Object>> getUnReviewedPaper(Integer reviewer_id) {
        List<Map<String,Object>> unReviewedPaper = reviewMapper.getUnReviewedPaper(reviewer_id);
        return unReviewedPaper;
    }

    @Override
    public List<Map<String, Object>> searchReviewedPaperByTitle(Map<String, Object> map) {
        List<Map<String,Object>> reviewedPaper = reviewMapper.searchReviewedPaperByTitle(map);
        return reviewedPaper;
    }

    @Override
    public List<Map<String, Object>> searchUnReviewedPaperByTitle(Map<String, Object> map) {
        List<Map<String,Object>> unReviewedPaper = reviewMapper.searchUnReviewedPaperByTitle(map);
        return unReviewedPaper;
    }

    @Override
    public Integer updateComment(Map<String,Object> map) {
        Integer res = reviewMapper.updateComment(map);
        return res;
    }

    @Override
    public Integer addComment(Map<String, Object> map) {
        Integer res = reviewMapper.addComment(map);
        return res;
    }


}
