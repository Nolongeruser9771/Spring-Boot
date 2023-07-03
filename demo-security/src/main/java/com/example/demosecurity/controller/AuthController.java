package com.example.demosecurity.controller;

import com.example.demosecurity.model.TokenConfirm;
import com.example.demosecurity.model.User;
import com.example.demosecurity.repository.TokenConfirmRepository;
import com.example.demosecurity.repository.UserRepository;
import com.example.demosecurity.request.LoginRequest;
import com.example.demosecurity.service.MailService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    private MailService mailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenConfirmRepository tokenConfirmRepository;

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

    @PostMapping("/forgot-password")
    public ResponseEntity<?> testSendMail(@RequestParam String email) {
        //Kiểm tra email có tồn tại trong database ko
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found user");
                });
        //Nếu email đã có trong db, tạo ra token -> lưu vào db
        //Token là chuỗi id generate ngẫu nhiên UUID
        TokenConfirm token = TokenConfirm.builder().
                token(UUID.randomUUID().toString())
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusMinutes(10))
                .user(user)
                .build();
        tokenConfirmRepository.save(token);
        //Send email chứa token
        //link: http://localhost:8080/doi-mat-khau/{token}
        mailService.sendMail(
                user.getEmail(),
                "Quên mật khẩu "+ user.getName(),
                "Link: "+ "http://localhost:8080/doi-mat-khau/"+ token.getToken());
        return ResponseEntity.ok().body("sent mail");
    }
}
