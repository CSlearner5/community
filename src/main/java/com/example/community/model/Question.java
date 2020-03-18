package com.example.community.model;

import lombok.Data;

@Data
public class Question {

    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long getCreate;
    private Long getModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
}
