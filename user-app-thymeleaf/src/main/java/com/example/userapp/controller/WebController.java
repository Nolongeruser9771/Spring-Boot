package com.example.userapp.controller;

import com.example.userapp.entity.FileServer;
import com.example.userapp.service.FileServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("")
public class WebController {
    @Autowired
    private FileServerService fileServerService;

    //1. *** Upload file ***
    @PostMapping("api/v1/users/{userId}/files")
    public FileServer uploadFile(@ModelAttribute("fileUpload") MultipartFile fileUpload,
                                 @PathVariable Integer userId) {
        FileServer fileServer = fileServerService.uploadFile(userId, fileUpload);
        return fileServer;
    }

    //2. Đọc file
    @GetMapping("api/v1/files/{id}")
    public ResponseEntity<?> readFile(@PathVariable Integer id) {
        FileServer fileServer = fileServerService.getFileById(id);
        return ResponseEntity.ok() //thông báo cho trình duyệt về kiểu dữ liệu trả về
                .contentType(MediaType.parseMediaType(fileServer.getType()))
                .body(fileServer.getData());
    }

    //3. Xóa file
    @DeleteMapping("api/v1/files/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable Integer id){
        fileServerService.deleteFile(id);
        return ResponseEntity.noContent().build();
    }
}
