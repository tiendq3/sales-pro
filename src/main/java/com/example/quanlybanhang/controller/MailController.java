package com.example.quanlybanhang.controller;

import com.example.quanlybanhang.model.request.Login;
import com.example.quanlybanhang.service.other.MailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class MailController {
    private final MailService mailService;

    @PostMapping("/send-otp")
    public void sendOTP(@RequestBody @Valid Login login) {
        mailService.sendOTP(login);
    }

    @GetMapping("/order-info")
    public void sendOrderInfo() {
    }
}
