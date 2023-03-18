package com.example.quanlybanhang;

import com.example.quanlybanhang.model.entities.User;
import com.example.quanlybanhang.model.enums.ERole;
import com.example.quanlybanhang.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
@AllArgsConstructor
public class SalesProApplication implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(SalesProApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User admin = User
                .builder()
                .email("adminvip@gmail.com")
                .password(passwordEncoder.encode("123456"))
                .roles(Set.of(ERole.ADMIN))
                .build();
        User user = userRepository.findUserByEmail("adminvip@gmail.com");
        if (user == null) userRepository.save(admin);
    }
}
