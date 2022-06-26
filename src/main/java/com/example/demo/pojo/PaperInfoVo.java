package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaperInfoVo {
    private int id;

    private int user_id;

    private int confer_id;

    private Date date;

    private String author;

    private String title;

    private String paper_abstract;

    private String keywords;

    private String location;

    private int distri_state;

    private int pass_state;
    private List<ReviewedPaperVo> review;
    private int paper_state;

    public PaperInfoVo(Paper paper,List<ReviewedPaperVo> reviewedPaperVo,int paper_state){
        this.id = paper.getId();
        this.user_id = paper.getUser_id();
        this.confer_id = paper.getConfer_id();
        this.date = paper.getDate();
        this.author = paper.getAuthor();
        this.title = paper.getTitle();
        this.paper_abstract = paper.getPaper_abstract();
        this.keywords= paper.getKeywords();
        this.location = paper.getLocation();
        this.distri_state = paper.getDistri_state();
        this.pass_state = paper.getPass_state();
        this.review = reviewedPaperVo;
        this.paper_state = paper_state;
    }
}
