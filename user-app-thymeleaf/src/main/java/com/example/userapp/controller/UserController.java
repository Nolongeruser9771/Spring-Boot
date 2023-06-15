package com.example.userapp.controller;

import com.example.userapp.entity.FileServer;
import com.example.userapp.entity.User;
import com.example.userapp.service.FileServerService;
import com.example.userapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private FileServerService fileServerService;

    //Danh sách users
    @GetMapping("users")
    public String getUsers(Model model) {
        List<User> userList = userService.getAllUsers();
        model.addAttribute("users", userList);
        return "index";
    }

    //Danh sách files của user
    @GetMapping("users/{id}/files")
    public String getFiles(@PathVariable Integer id, Model model) {
        List<FileServer> filesList = fileServerService.getFilesByUserId(id);
        model.addAttribute("files", filesList);
        model.addAttribute("userId", id);
        return "file_list";
    }
}
