package com.example.demosecurity.controller;

import com.example.demosecurity.security.IsAdmin;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    // Ai cũng có thể vào được
    @GetMapping("/")
    public String getHome() {
        return "index";
    }
    
    @GetMapping("/login")
    public String getLoginPage(Authentication authentication) {
        //Kiểm tra thông tin trong context holder
        //Nếu đối tượng đã xác thực -> trả về trang hiện tại
        if(authentication != null && authentication.isAuthenticated()) {
            return "redirect:/";
        }
        //Chưa -> trả về trang login
        return "login";
    }

    // Có role admin hoặc author mới có thể vào
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_AUTHOR')")
    @GetMapping("/profile")
    public String getProfile() {
        return "profile";
    }

    // Phải có role admin mới vào được
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @IsAdmin
    @GetMapping("/admin")
    public String getAdmin() {
        return "admin";
    }

    // Phải có role author mới vào được
    @PreAuthorize("hasRole('ROLE_AUTHOR')")
    @GetMapping("/author")
    public String getAuthor() {
        return "author";
    }
}
