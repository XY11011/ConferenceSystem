package com.example.demo.service;

import com.example.demo.pojo.Conference;
import com.example.demo.pojo.Paper;
import com.example.demo.pojo.ReviewedPaperVo;

import java.util.List;
import java.util.Map;

public interface PaperService {
    List<Paper> selectMyPaperByConference(Map<String,Object> map);
    List<ReviewedPaperVo> selectReviewByPaper(Integer paperId);
    //添加论文
    int addPaper(Paper paper);
}
