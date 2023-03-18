package com.example.quanlybanhang.service.other;

import com.example.quanlybanhang.model.dtos.MailMessage;
import com.example.quanlybanhang.model.entities.OTP;
import com.example.quanlybanhang.model.entities.Order;
import com.example.quanlybanhang.model.entities.Product;
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

    public void sendOrderInfo(Order order) throws MailException {
        StringBuilder content = new StringBuilder("______________________________________\n| Product\t\t\tAmount\t\t\tPrice |\n|______________________________________|");
        for (Product product : order.getItems().keySet()) {
            content
                    .append("\n|")
                    .append(product.getName())
                    .append("\t\t\t")
                    .append(order.getItems().get(product))
                    .append("\t\t\t")
                    .append(product.getFinalPrice())
                    .append("$ |");
        }
        content.append("\nSTATUS: ").append(order.getStatusOrder().toString().toUpperCase());
        MailMessage mailMessage = MailMessage
                .builder()
                .email(order.getEmail())
                .title("SalesPro: Your Order")
                .content(content.toString())
                .build();
        mailSender(mailMessage);
    }
}
