package com.example.demologin.controllers;

import com.example.demologin.models.ReqLogin;
import com.example.demologin.models.ResponseObject;
import com.example.demologin.models.UserDTO;
import com.example.demologin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserControllers {

    @Autowired
    private UserService userService;

    //get all users
    @RequestMapping("/users")
    public ResponseEntity<ResponseObject> getAllUser() {
        List<UserDTO> response = userService.getUserList();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","Get list successfully", response)
        );
    }

    //post login request
    @PostMapping("/login")
    public ResponseEntity<ResponseObject> postLogin(@RequestBody ReqLogin req) {
        UserDTO foundUser = userService.getUserByLoginInfo(req);
        if (foundUser!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","Login successfully",foundUser)
            );
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("FAILED","Wrong password or username","")
        );
    }

}
