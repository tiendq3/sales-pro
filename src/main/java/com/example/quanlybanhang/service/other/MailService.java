package com.example.quanlybanhang.service.other;

import lombok.Data;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Data
public class MailService {

    private final JavaMailSender javaMailSender;

    public void mailSender(String email, String content, String subject) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setText(content);
        message.setSubject(subject);
        javaMailSender.send(message);
    }
}
