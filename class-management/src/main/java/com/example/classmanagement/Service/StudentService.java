package com.example.classmanagement.Service;

import com.example.classmanagement.dto.StudentDTO;
import com.example.classmanagement.dto.UpdateStudentRequest;

public interface StudentService {
    StudentDTO updateStudent(Integer studentId, UpdateStudentRequest updateStudentRequest);
    void deleteStudent(Integer studentId);
}
