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
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private FileServerService fileServerService;

    //Danh sách users
    @GetMapping("")
    public String getUsers(Model model) {
        List<User> userList = userService.getAllUsers();
        model.addAttribute("users", userList);
        return "index";
    }

    //Danh sách files của user
    @GetMapping("/{id}/files")
    public String getFiles(@PathVariable Integer id, Model model) {
        List<FileServer> filesList = fileServerService.getFilesByUserId(id);
        model.addAttribute("files", filesList);
        model.addAttribute("userId", id);
        return "file_list";
    }

    //1. *** Upload file ***
    @PostMapping("{userId}/files")
    public String uploadFile(@ModelAttribute("fileUpload") MultipartFile fileUpload,
                             @PathVariable Integer userId) {
        fileServerService.uploadFile(userId, fileUpload);
        return "redirect:/api/v1/users/" + userId +"/files";
    }
}
