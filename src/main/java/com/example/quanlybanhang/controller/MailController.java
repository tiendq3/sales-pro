package com.example.quanlybanhang.controller;

import com.example.quanlybanhang.model.request.Login;
import com.example.quanlybanhang.service.other.MailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@Slf4j
public class MailController {
    private final MailService mailService;

    @PostMapping("api/v1/send-otp")
    public void sendOTP(@RequestBody @Valid Login login) {
        mailService.sendOTP(login);
    }
}
