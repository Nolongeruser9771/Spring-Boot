package com.example.shoppingcart.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true, setterPrefix = "with")
public class Course {
    private Integer id;
    private String name;
    private String description;
    private String type;
    private List<Category> categories;
    private String thumbnail;
    private Integer price;
    private Double rating;
    private Integer userId;
}
