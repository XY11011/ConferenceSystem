package com.example.demo.service;

import java.util.List;
import java.util.Map;

public interface ReviewService {
    List<Map<String,Object>> getReviewedPaper(Integer reviewer_id);
    List<Map<String,Object>> getUnReviewedPaper(Integer reviewer_id);
    List<Map<String,Object>> searchReviewedPaperByTitle(Map<String,Object> map);
    List<Map<String,Object>> searchUnReviewedPaperByTitle(Map<String,Object> map);
    Integer updateComment(Map<String,Object> map);
    Integer addComment(Map<String,Object> map);
}
