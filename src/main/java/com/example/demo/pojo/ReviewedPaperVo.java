package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewedPaperVo {
    private int reviewer_id;
    private String username;
    private int review_state;
    private int rank;
    private String Comment;
}
