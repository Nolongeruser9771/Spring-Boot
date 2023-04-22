package com.example.apiuser.service;

import com.example.apiuser.entity.User;
import com.example.apiuser.model.CourseDTO;
import com.example.apiuser.model.UpsertCourseRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {

    List<CourseDTO> getListByPara(String name, String type, String topic);

    User getUserById(Integer id);

    List<CourseDTO> getAll();

    CourseDTO getCourseById(Integer id);

    CourseDTO addNewCourse(UpsertCourseRequest req);

    CourseDTO updatedCourse(UpsertCourseRequest req, Integer id);

    void deleteCourse(Integer id);
}
