package com.example.demosecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    public JavaMailSender emailSender;

    public void sendMail(String user, String subject, String content) {
        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo("abc@gmail.com");
        message.setSubject("Test Simple Email");
        message.setText("Hello World");

        // Send Message!
        emailSender.send(message);
    }
}
