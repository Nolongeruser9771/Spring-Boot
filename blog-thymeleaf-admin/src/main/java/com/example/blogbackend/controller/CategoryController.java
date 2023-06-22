package com.example.blogbackend.controller;

import com.example.blogbackend.dto.projection.CategoryWebPublic;
import com.example.blogbackend.request.UpsertCategoryRequest;
import com.example.blogbackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //1.Lấy ds category (có phân trang, mặc định là pageSize = 10) (Phân trang bên phía client)
    @GetMapping("api/v1/admin/categories")
    public ResponseEntity<?> getCategoryList(){
        return ResponseEntity.ok().body(categoryService.getCategoryList());
    }

    //2.Thêm category (Lưu ý tên category không được trùng nhau)
    //POST : api/v1/admin/categories
    @PostMapping("api/v1/admin/categories")
    public ResponseEntity<?> addNewCategory(UpsertCategoryRequest request){
        CategoryWebPublic newCategory = categoryService.addCategory(request);
        return ResponseEntity.ok().body(newCategory);
    }

    //3.Cập nhật category (Lưu ý tên category không được trùng nhau)
    //PUT : api/v1/admin/categories/{id}
    @PutMapping("api/v1/admin/categories/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id, UpsertCategoryRequest request){
        CategoryWebPublic updatedCategory = categoryService.updateCategory(id, request);
        return ResponseEntity.ok().body(updatedCategory);
    }

    //4.Xóa category (xóa blog áp dụng category trong bảng trung gian, không xóa blog trong bảng blog)
    //DELETE : api/v1/admin/categories/{id}
    @DeleteMapping("api/v1/admin/categories/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id){
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
