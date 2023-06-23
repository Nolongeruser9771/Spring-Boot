package com.example.demothymeleafsecurity.controller;

import com.example.demothymeleafsecurity.security.IsAdmin;
import com.example.demothymeleafsecurity.security.IsUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    //1. Home page - Ai cũng vào được
    @GetMapping("/")
    public String getHome() {
        return "index";
    }

    //2. Xem dashboard - Chỉ admin, sale mới vào được
    @PreAuthorize("hasRole('ADMIN') or hasRole('SALE')")
    @GetMapping("/dashboard")
    public String getDashboard() {
        return "dashboard";
    }

    //3. Quản lí user - Chỉ admin mới vào được
    @IsAdmin
    @GetMapping("/user-list")
    public String getUserList() {
        return "user-list";
    }

    //4. Quản lí sản phẩm - Chỉ admin, sale mới vào được
    @PreAuthorize("hasRole('ADMIN') or hasRole('SALE')")
    @GetMapping("/product-list")
    public String getProductList() {
        return "product-list";
    }

    //5. Quản lí bài viết - Chỉ admin, sale, author mới vào được
    @PreAuthorize("hasRole('ADMIN') or hasRole('SALE') or hasRole('AUTHOR')")
    @GetMapping("/post-list")
    public String getPostList() {
        return "post-list";
    }

    //6. Xem thông tin cá nhân - Chỉ user mới vào được
    @IsUser
    @GetMapping("/profile")
    public String getProfile() {
        return "profile";
    }
}
