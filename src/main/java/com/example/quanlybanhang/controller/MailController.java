package com.example.quanlybanhang.controller;

import com.example.quanlybanhang.service.other.MailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class MailController {
    private final MailService mailService;

    @GetMapping("api/v1/send-email")
    public void sendEmail() {
        try {
            mailService.mailSender("tiendq3@gmail.com", "helooooo", "test");
        } catch (MailException e) {
            log.error(e.toString());
        }
    }
}
