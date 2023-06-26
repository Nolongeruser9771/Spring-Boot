package com.example.blogbackend.controller;

import com.example.blogbackend.dto.projection.CategoryPublic;
import com.example.blogbackend.dto.projection.CategoryWebPublic;
import com.example.blogbackend.request.UpsertCategoryRequest;
import com.example.blogbackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //1.Lấy ds category (có phân trang, mặc định là pageSize = 10)
    //GET : admin/categories?page={page}&pageSize={pageSize}
    @GetMapping("admin/categories")
    public String getCategoryList(@RequestParam(defaultValue = "1", required = false) Integer page,
                                  @RequestParam(defaultValue = "10", required = false) Integer pageSize){
        Page<CategoryPublic> categoryList = categoryService.getCategoryPage(page-1, pageSize);
        //sau khi thêm giao diện sẽ xử lí hiển thị
        return "admin/category/category-list";
    }

    //2.Thêm category (Lưu ý tên category không được trùng nhau)
    //POST : api/v1/admin/categories
    @PostMapping("api/v1/admin/categories")
    public ResponseEntity<?> addNewCategory(@RequestBody UpsertCategoryRequest request){
        CategoryPublic newCategory = categoryService.addCategory(request);
        return ResponseEntity.ok().body(newCategory);
    }

    //3.Cập nhật category (Lưu ý tên category không được trùng nhau)
    //PUT : api/v1/admin/categories/{id}
    @PutMapping("api/v1/admin/categories/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id, @RequestBody UpsertCategoryRequest request){
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
