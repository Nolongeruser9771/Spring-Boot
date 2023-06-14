package com.example.classmanagement.controller;

import com.example.classmanagement.Service.ClazzService;
import com.example.classmanagement.Service.StudentService;
import com.example.classmanagement.dto.AddStudentRequest;
import com.example.classmanagement.dto.ClazzDTO;
import com.example.classmanagement.exception.BadRequestException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @Autowired
    private StudentService studentService;

    //1. Hiển thị thông tin chi tiết của class
    @GetMapping("")
    public ResponseEntity<?> getAllClazzInfo(){
        List<ClazzDTO> clazzes = clazzService.findAllClazz();
        return ResponseEntity.ok().body(clazzes);
    }

    //2. Thêm học viên vào class
    @PostMapping("/{clazzId}")
    public ResponseEntity<?> addStudentIntoClazz(@PathVariable("clazzId") Integer clazzId,
                                                 @RequestBody @Valid AddStudentRequest addStudentRequest,
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
        clazzService.addStudentToClazz(clazzId, addStudentRequest);
        return ResponseEntity.ok().body("New student added successfully!");
    }

    //3. Có 1 bạn xin nghỉ học, hãy giúp thầy xóa bạn đó khỏi lớp học
    @PutMapping("{clazzId}/{studentId}")
    public ResponseEntity<?> deleteStudentFromClazz(@PathVariable("clazzId") Integer clazzId,
                                                    @PathVariable("studentId") Integer studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok().body("Delete student id "+ studentId+" from class "+ clazzId+" successfully.");
    }
}
