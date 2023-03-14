package com.example.quanlybanhang.service.other;

import com.example.quanlybanhang.model.dto.MailMessage;
import com.example.quanlybanhang.model.entity.OTP;
import com.example.quanlybanhang.model.request.Login;
import com.example.quanlybanhang.repository.OTPRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;

    private final OTPRepository otpRepository;

    public void mailSender(MailMessage mailMessage) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailMessage.getEmail());
        message.setText(mailMessage.getContent());
        message.setSubject(mailMessage.getTitle());
        javaMailSender.send(message);
    }

    public void sendOTP(Login login) throws MailException {
        String otpCode = (int) (10000 * Math.random()) + "";
        OTP otp = OTP
                .builder()
                .email(login.getEmail())
                .otp(otpCode)
                .createAt(Instant.now())
                .build();
        otpRepository.save(otp);
        MailMessage mailMessage = MailMessage
                .builder()
                .email(login.getEmail())
                .title("Email verification")
                .content("Your OTP: " + otp.getOtp())
                .build();
        mailSender(mailMessage);
    }
}
