package com.example.apiuser.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Course {
    static Integer AUTO_COURSE_ID = 0;
    private Integer id;
    private String name;
    private String description;
    private String type;
    private List<String> topics;
    private String thumbnail;
    private Integer userId;

    public Course(String name, String description, String type, List<String> topics, String thumbnail, Integer userId) {
        AUTO_COURSE_ID++;
        this.id = AUTO_COURSE_ID;
        this.name = name;
        this.description = description;
        this.type = type;
        this.topics = topics;
        this.thumbnail = thumbnail;
        this.userId = userId;
    }
}
