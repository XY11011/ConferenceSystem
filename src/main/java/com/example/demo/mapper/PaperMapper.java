package com.example.demo.mapper;

import com.example.demo.pojo.Conference;
import com.example.demo.pojo.Paper;
import com.example.demo.pojo.ReviewedPaperVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface PaperMapper {
    List<Paper> selectMyPaperByConference(Map<String,Object> map);
    List<ReviewedPaperVo> selectReviewByPaper(Integer paperId);
    //添加论文
    int addPaper(Paper paper);
}
