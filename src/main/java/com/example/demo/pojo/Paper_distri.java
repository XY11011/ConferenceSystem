package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 梦里花开又半夏~
 * @date 2022/5/1 15:58
 */
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paper_distri {
    private int id;

    private int paper_id;

    private int reviewer_id;

    private int confer_id;

    private int review_state;

    private int rank;

    private String comment;
}
