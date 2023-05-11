package com.example.shoppingcart.controller;

import com.example.shoppingcart.service.FileService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/thumbnail")
public class FileController {
    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<byte[]> getThumbnailURL(@PathVariable Integer id) throws IOException {
        String filename = fileService.getThumnailById(id);
        byte[] bytes = fileService.readFile(filename);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(bytes);
    }
}
