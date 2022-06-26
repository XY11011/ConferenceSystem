package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ReviewMapper {
    List<Map<String,Object>> getReviewedPaper(Integer reviewer_id);
    List<Map<String,Object>> getUnReviewedPaper(Integer reviewer_id);
    List<Map<String,Object>> searchReviewedPaperByTitle(Map<String,Object> map);
    List<Map<String,Object>> searchUnReviewedPaperByTitle(Map<String,Object> map);
    Integer updateComment(Map<String,Object> map);
    Integer addComment(Map<String,Object> map);
}
