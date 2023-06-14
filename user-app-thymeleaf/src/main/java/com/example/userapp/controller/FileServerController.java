package com.example.userapp.controller;

import com.example.userapp.entity.FileServer;
import com.example.userapp.service.FileServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/v1/files")
public class FileServerController {
    @Autowired
    private FileServerService fileServerService;

    //2. Đọc file
    @GetMapping("/{id}")
    public ResponseEntity<?> readFile(@PathVariable Integer id) {
        FileServer fileServer = fileServerService.getFileById(id);
        return ResponseEntity.ok() //thông báo cho trình duyệt về kiểu dữ liệu trả về
                .contentType(MediaType.parseMediaType(fileServer.getType()))
                .body(fileServer.getData());
    }

    //3. Xóa file
    @GetMapping("/delete/{id}")
    public String deleteFile(@PathVariable Integer id,
                             @RequestParam Integer userId){
        fileServerService.deleteFile(id);
        return "redirect:/api/v1/users/" + userId +"/files";
    }
}
