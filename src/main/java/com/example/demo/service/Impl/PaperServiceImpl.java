package com.example.demo.service.Impl;

import com.example.demo.mapper.PaperMapper;
import com.example.demo.pojo.Conference;
import com.example.demo.pojo.Paper;
import com.example.demo.pojo.ReviewedPaperVo;
import com.example.demo.service.PaperService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class PaperServiceImpl implements PaperService {

    @Resource
    PaperMapper paperMapper;

    @Override
    public List<Paper> selectMyPaperByConference(Map<String, Object> map) {
        return paperMapper.selectMyPaperByConference(map);
    }

    @Override
    public List<ReviewedPaperVo> selectReviewByPaper(Integer paperId) {
        return paperMapper.selectReviewByPaper(paperId);
    }

    @Override
    public int addPaper(Paper paper) {
        int res= paperMapper.addPaper(paper);
        return res;
    }
}
