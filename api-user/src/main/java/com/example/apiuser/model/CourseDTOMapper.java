package com.example.apiuser.model;

import com.example.apiuser.entity.Course;
import com.example.apiuser.entity.User;

public class CourseDTOMapper {
    public static CourseDTO toCourseDTOMapper(Course course, User user) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());
        courseDTO.setDescription(course.getDescription());
        courseDTO.setType(course.getType());
        courseDTO.setTopics(course.getTopics());
        courseDTO.setThumbnail(course.getThumbnail());
        courseDTO.setUser(user);

        return courseDTO;
    }
}
