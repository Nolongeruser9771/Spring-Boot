package com.example.demosecurity.controller;

import com.example.demosecurity.model.TokenConfirm;
import com.example.demosecurity.repository.TokenConfirmRepository;
import com.example.demosecurity.security.IsAdmin;
import com.example.demosecurity.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class WebController {

    @Autowired
    private MailService mailService;

    @Autowired
    private TokenConfirmRepository tokenConfirmRepository;

    // Ai cũng có thể vào được
    @GetMapping("/")
    public String getHome() {
        return "index";
    }

    @GetMapping("/login")
    public String getLoginPage(Authentication authentication) {
        //Kiểm tra thông tin trong context holder
        //Nếu đối tượng đã xác thực -> trả về trang hiện tại
        if(authentication != null && authentication.isAuthenticated()) {
            return "redirect:/";
        }
        //Chưa -> trả về trang login
        return "login";
    }

    @GetMapping("/forgot-password")
    public String getForgotPasswordPage() {
        return "forgot-password";
    }

    // Có role admin hoặc author mới có thể vào
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_AUTHOR')")
    @GetMapping("/profile")
    public String getProfile() {
        return "profile";
    }

    // Phải có role admin mới vào được
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @IsAdmin
    @GetMapping("/admin")
    public String getAdmin() {
        return "admin";
    }

    // Phải có role author mới vào được
    @PreAuthorize("hasRole('ROLE_AUTHOR')")
    @GetMapping("/author")
    public String getAuthor() {
        return "author";
    }

    //Send -> send email xác nhận quên mật khẩu
    //Nội dung email: link xác thực

    @GetMapping("/api/send-email")
    public ResponseEntity<?> testSendMail() {
        mailService.sendMail("Nguyên Nguyễn",
                "Hi keoooo!",
                "Marry me?!");
        return ResponseEntity.ok().body("sent mail");
    }

    @GetMapping("/doi-mat-khau/{token}")
    public String getUpdatePasswordPage(@PathVariable String token, Model model) {
        //Kiểm tra token có đúng không
        Optional<TokenConfirm> optionalTokenConfirm = tokenConfirmRepository.findByToken(token);
        if (optionalTokenConfirm.isEmpty()) {
            model.addAttribute("isValid", false);
            model.addAttribute("message", "token không hợp lệ");
            return "update-password";
        }

        TokenConfirm tokenConfirm = optionalTokenConfirm.get();
        //Kiểm tra token đã được kích hoạt chưa
        if (tokenConfirm.getConfirmedAt()!=null) {
            model.addAttribute("isValid", false);
            model.addAttribute("message", "token đã được kích hoạt");
            return "update-password";
        }

        //Kiểm tra token đã hết hạn chưa
        if (tokenConfirm.getExpiredAt().isBefore(LocalDateTime.now())) {
            if (tokenConfirm.getConfirmedAt()!=null) {
                model.addAttribute("isValid", false);
                model.addAttribute("message", "token đã hết hạn");
                return "update-password";
            }
        }

        tokenConfirm.setConfirmedAt(LocalDateTime.now());
        tokenConfirmRepository.save(tokenConfirm);

        model.addAttribute("isValid", true);
        model.addAttribute("message", "token hợp lệ");
        return "update-password";
    }
}
