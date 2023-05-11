package com.example.shoppingcart.repository.db;

import com.example.shoppingcart.entity.Category;
import com.example.shoppingcart.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseDB {
    public static List<Course> courses = new ArrayList<>(List.of(
            Course.builder().withCategories(List.of(new Category(1,"backend"),new Category(2,"java"))).withDescription("professional").withId(1).withName("Java course").withPrice(20000000).withRating(3.5d).withThumbnail("img12.png").withType("online").withUserId(1).build(),
            Course.builder().withCategories(List.of(new Category(3,"full-stack"))).withDescription("professional").withId(2).withName("Fullstack course").withPrice(24000000).withRating(4d).withThumbnail("img11.png").withType("online").withUserId(2).build(),
            Course.builder().withCategories(List.of(new Category(4,"frontend"))).withDescription("professional").withId(3).withName("Frontend course").withPrice(14000000).withRating(3.8d).withThumbnail("img10.png").withType("online").withUserId(3).build()
    ));
}
