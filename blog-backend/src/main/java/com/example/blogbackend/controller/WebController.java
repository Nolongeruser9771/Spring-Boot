package com.example.blogbackend.controller;

import com.example.blogbackend.dto.projection.BlogPublic;
import com.example.blogbackend.dto.projection.CategoryWebPublic;
import com.example.blogbackend.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/public")
public class WebController {

    @Autowired
    private WebService webService;

    //1. Lấy danh sách blog
        //GET : api/v1/public/blogs?page={pageValue}&pageSize=${pageSizeValue}
        //return Page<Blog>
    @GetMapping("/blogs")
    public ResponseEntity<?> getAllBlog(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        Page<BlogPublic> blogPage = webService.getAllBlog(page-1,pageSize);
        return ResponseEntity.ok().body(blogPage);
    }

    //2. Tìm kiếm blog
        //GET : api/v1/public/search?term={termValue}
        //return List<Blog>
    @GetMapping("/search")
    public ResponseEntity<?> searchBlogs(@RequestParam(value = "term") String termValue) {
        List<BlogPublic> blogList = webService.searchBlog(termValue);
        return ResponseEntity.ok().body(blogList);
    }

    //3. Lấy danh sách category
        //GET : api/v1/public/categories
        //return List<CategoryDto>
    @GetMapping("/categories")
    public ResponseEntity<?> getAllCategories() {
        List<CategoryWebPublic> categories = webService.getAllCategory();
        return ResponseEntity.ok().body(categories);
    }

    //4. Lấy danh sách category được sử dụng nhiều nhất
        //GET : api/v1/public/category/top5
        //return List<CategoryDto>
    @GetMapping("/category/top5")
    public ResponseEntity<?> getTop5Categories() {
        List<CategoryWebPublic> categories = webService.getTop5Category();
        return ResponseEntity.ok().body(categories);
    }

    //5. Lấy danh sách bài viết áp dụng category
        //GET : api/v1/public/category/{categoryName}
        //return List<Blog>
    @GetMapping("category/{categoryName}")
    public ResponseEntity<?> getBlogsInCategory(@PathVariable String categoryName) {
        List<BlogPublic> blogs = webService.getBlogsInCategory(categoryName);
        return ResponseEntity.ok().body(blogs);
    }

    //6. Lấy chi tiết bài viết
        //GET : api/v1/public/blogs/{blogId}/{blogSlug}
        //return Blog
    @GetMapping("blogs/{blogId}/{blogSlug}")
    public ResponseEntity<?> getBlogsInCategory(@PathVariable Integer blogId,
                                                @PathVariable String blogSlug) {
        BlogPublic blog = webService.getBlogDetail(blogId,blogSlug);
        return ResponseEntity.ok().body(blog);
    }

}
