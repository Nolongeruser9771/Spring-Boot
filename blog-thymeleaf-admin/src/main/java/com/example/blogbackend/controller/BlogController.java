package com.example.blogbackend.controller;

import com.example.blogbackend.dto.projection.BlogPublic;
import com.example.blogbackend.request.UpsertBlogRequest;
import com.example.blogbackend.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;

    //1. Lấy danh sách blog (có phân trang, mặc đinh pageSize = 10)
    @GetMapping("/admin/blogs")
    public String getAllBlogsPage(@RequestParam(defaultValue = "1", required = false) Integer page,
                              @RequestParam(defaultValue = "10", required = false) Integer pageSize,
                              Model model) {
        Page<BlogPublic> blogList = blogService.getBlogPage(page-1, pageSize);
        model.addAttribute("page", blogList);
        model.addAttribute("currentPage", page);
        return "admin/blog/blog-index";
    }

    //2. Lấy danh sách blog user đang login
    @GetMapping("/admin/blogs/own-blogs")
    public String getOwnBlogsPage(Model model) {
        //user_id fixed to 1
        List<BlogPublic> blogList = blogService.getAllOwnBlog();
        model.addAttribute("blogList", blogList);
        return "admin/blog/own-blog";
    }

    //3. Lấy chi tiết blog theo blog id
    //GET : admin/blogs/{id}/detail (Trả về Giao diện)
    @GetMapping("admin/blogs/{id}/detail")
    public String getBlogById(@PathVariable Integer id,
                              Model model) {
        BlogPublic blog = blogService.getBlogDetail(id);
        model.addAttribute("blog", blog);
        return "admin/blog/blog-detail";
    }

    //4. Thêm blog mới
    //POST : api/v1/admin/blogs
    @PostMapping("api/v1/admin/blogs")
    public ResponseEntity<?> addBlog(@RequestBody UpsertBlogRequest request) {
        BlogPublic newBlog = blogService.addNewBlog(request);
        return ResponseEntity.ok().body(newBlog);
    }

    //5. Cập nhật blog
    //PUT : api/v1/admin/blogs/{id}
    @PutMapping("api/v1/admin/blogs/{id}")
    public ResponseEntity<?> updateBlog(@PathVariable Integer id, @RequestBody UpsertBlogRequest request) {
        BlogPublic updatedBlog = blogService.updateBlog(id, request);
        return ResponseEntity.ok().body(updatedBlog);
    }

    //6. Xóa blog (xóa luôn comment liên quan)
    //DELETE : api/v1/admin/blogs/{id}
    @DeleteMapping("api/v1/admin/blogs/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable Integer id) {
        blogService.deleteBlog(id);
        return ResponseEntity.noContent().build();
    }
}
