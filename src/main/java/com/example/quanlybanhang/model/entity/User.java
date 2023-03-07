package com.example.quanlybanhang.model.entity;

import com.example.quanlybanhang.config.AppConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", unique = true, nullable = false)
    private String username;

    @JsonIgnore
    private String password;

    @NotNull(message = "Email is required")
    @NotEmpty(message = "Email is required")
    @Email(message = "Please provide a valid email")
    private String email;

    @Pattern(regexp = AppConstants.PHONE_REGEX,
            message = "Please provide a valid phone number")
    private String phone;

    private String lastName;

    @NotNull(message = "Full name is required")
    @NotEmpty(message = "Full name is required")
    private String first_name;

    private String gender;

    private Instant birthday;

    private String avatar;

    private String address;

    private String city;

    private String country;

    private String active_code;

    private String status;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Role> roles;
}
