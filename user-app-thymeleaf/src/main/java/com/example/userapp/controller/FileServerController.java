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
@RequestMapping("")
public class FileServerController {
    @Autowired
    private FileServerService fileServerService;


}
