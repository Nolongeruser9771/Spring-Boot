package com.example.demosecurity.controller;

import com.example.demosecurity.request.LoginRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login-handle")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpSession session){
        //Tạo đối tượng xác thực (token -> làm tham số đầu vào cho AuthenticationManager)
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );

        //Tiến hành xác thực (Gọi phương thức tương ứng của AuthenticationManager)
        try {
            Authentication authentication = authenticationManager.authenticate(token);

            //Lưu đối tượng được xác thực vào contextHolder
            SecurityContextHolder.getContext().setAuthentication(authentication);

            //Lưu vào trong session
            session.setAttribute("MY_SESSION", authentication.getName()); //lưu email vào session

            return ResponseEntity.ok().body("login success");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("login failed");
        }
    }
    //Client -> Server -> (success) Tạo session -> session_id lưu vào trong cookie -> lưu trữ phía Client
}
