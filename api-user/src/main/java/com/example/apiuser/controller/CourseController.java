package com.example.apiuser.controller;

import com.example.apiuser.model.CourseDTO;
import com.example.apiuser.model.CoursePageDTO;
import com.example.apiuser.model.UpsertCourseRequest;
import com.example.apiuser.service.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Validated
public class CourseController {

    @Autowired
    CourseService courseService;

    //API USER
    //1. Xem danh sách tất cả khóa học (type || name || topic not required)
    @GetMapping(value = "/courses")
    public ResponseEntity<?> getAllCourse(@RequestParam(value = "name", required = false) String name,
                                          @RequestParam(value = "type", required = false) String type,
                                          @RequestParam(value = "topic", required = false) String topic){
        List<CourseDTO> lists = courseService.getListByPara(name,type,topic);
        return ResponseEntity.ok(lists);
    }
    //Xem hết danh sách (test thử)
    @GetMapping("/courses/getAll")
    public ResponseEntity<?> getAll(){
        List<CourseDTO> list = courseService.getAll();
        return ResponseEntity.ok(list);
    }

    //2. Xem thông tin của 1 khóa học cụ thể (với role user & role admin)
    @GetMapping(value = {"/courses/{id}", "/admin/courses/{id}"})
    public ResponseEntity<?> getCourseById(@PathVariable("id") Integer id) {
        CourseDTO foundedUser = courseService.getCourseById(id);
        return ResponseEntity.ok(foundedUser);
    }

    //API Admin
    //1. Xem danh sách khóa học (có phân trang)
    //GET /api/v1/admin/courses?page={pageValue}&pageSize={pageSizeValue}
    @GetMapping("/admin/courses")
    public ResponseEntity<?> getCourseByPage(@RequestParam(defaultValue = "1", required = false) Integer page,
                                             @RequestParam(defaultValue = "10", required = false) Integer pageSize) {
        CoursePageDTO coursePage= courseService.getCourseByPage(page,pageSize);
        return ResponseEntity.ok(coursePage);
    }

    //2.Tạo khóa học mới (POST /api/v1/admin/courses)
    @PostMapping("/admin/courses")
    public ResponseEntity<?> addCourse(@Valid @RequestBody UpsertCourseRequest req) {
        CourseDTO newCourse = courseService.addNewCourse(req);
        return ResponseEntity.ok(newCourse);
    }

    //3. Lấy chi tiết khóa học (GET /api/v1/admin/courses/{id})
    //defined above

    //4. Cập nhật thông tin khóa (PUT /api/v1/admin/courses/{id})
    @PutMapping("/admin/courses/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable("id") Integer id,
                                          @Valid @RequestBody UpsertCourseRequest req) {
        CourseDTO updatedCourse = courseService.updatedCourse(req, id);
        return ResponseEntity.ok(updatedCourse);
    }

    //5. Xóa khóa học (DELETE /api/v1/admin/courses/{id})
    @DeleteMapping("/admin/courses/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable("id") Integer id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok("Delete course id "+ id+ " successfully");
    }
}
