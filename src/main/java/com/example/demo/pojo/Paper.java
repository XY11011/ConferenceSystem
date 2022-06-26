package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 梦里花开又半夏~
 * @date 2022/5/1 15:51
 */
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paper {
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
    public Paper(int user_id, int confer_id, Date date, String author, String title, String paper_abstract, String keywords, String location, int distri_state, int pass_state) {
        this.user_id = user_id;
        this.confer_id = confer_id;
        this.date = date;
        this.author = author;
        this.title = title;
        this.paper_abstract = paper_abstract;
        this.keywords = keywords;
        this.location = location;
        this.distri_state = distri_state;
        this.pass_state = pass_state;
    }
}
