package com.example.classmanagement.controller;

import com.example.classmanagement.Service.StudentService;
import com.example.classmanagement.dto.StudentDTO;
import com.example.classmanagement.dto.UpdateStudentRequest;
import com.example.classmanagement.exception.BadRequestException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    //3. Cập nhật thông tin 1b học viên dựa vào Id
    @PutMapping("/{studentId}")
    public ResponseEntity<?> updateStudentById(@PathVariable("studentId") Integer studentId,
                                               @RequestBody @Valid UpdateStudentRequest request,
                                               BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors("name")) {
            throw new BadRequestException("Field name has error");
        }
        if (bindingResult.hasFieldErrors("age")) {
            throw new BadRequestException("Field age has error");
        }
        if (bindingResult.hasFieldErrors("ranking")) {
            throw new BadRequestException("Field ranking has error");
        }
        StudentDTO updatedStudent = studentService.updateStudent(studentId, request);
        return ResponseEntity.ok().body(updatedStudent);
    }
}
