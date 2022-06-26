package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 梦里花开又半夏~
 * @date 2022/5/1 15:47
 */
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Conference {
    private int id;

    private String title;

    private Date date_start;

    private Date date_create;

    private Date date_end;

    private String location;

    private String area;

    private String topic;

    private String introduction;

    private String confer_link;

    private String external_link;

    private int announcer_id;

    public Conference(int id,String title, Date date_start, Date date_end, String location, String area, String topic, String introduction, String confer_link, String external_link) {
        this.id=id;
        this.title = title;
        this.date_start = date_start;
        this.date_end = date_end;
        this.location = location;
        this.area = area;
        this.topic = topic;
        this.introduction = introduction;
        this.confer_link = confer_link;
        this.external_link = external_link;
    }
}
