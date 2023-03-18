package com.example.quanlybanhang.model.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.Instant;

@Entity
@Table(name = "otp")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OTP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Email should be valid")
    private String email;

    private String otp;

    private Instant createAt;
}
