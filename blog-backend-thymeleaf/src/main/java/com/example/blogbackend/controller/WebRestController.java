package com.example.blogbackend.controller;

import com.example.blogbackend.dto.projection.BlogPublic;
import com.example.blogbackend.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WebRestController {
    @Autowired
    private WebService webService;

    @GetMapping(value = "/search/")
    public ResponseEntity<?> searchBlog(@RequestParam("search") String searchValue) {
        List<BlogPublic> blog = webService.searchBlog(searchValue);
        return ResponseEntity.ok().body(blog);
    }
}
