package com.example.shoppingcart.repository;

import com.example.shoppingcart.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.example.shoppingcart.repository.db.CourseDB.courses;

@Repository
public class CourseRepository {
    public Optional<Course> getCourseById(int courseId) {
        return courses.stream()
                .filter(course -> course.getId() == courseId)
                .findFirst();
    }
}
