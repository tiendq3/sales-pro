package com.example.quanlybanhang.model.entity;

import com.example.quanlybanhang.config.AppConstants;
import com.example.quanlybanhang.model.enums.ERole;
import com.example.quanlybanhang.model.enums.EStatusAccount;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
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

    @Email(message = "Email should be valid")
    @NotEmpty
    @Column(name = "email", unique = true, length = 50)
    private String email;

    @JsonIgnore
    @NotEmpty
    private String password;

    @Pattern(regexp = AppConstants.PHONE_REGEX,
            message = "Please provide a valid phone number")
    @Nullable
    private String phone;

    private String name;

    private String gender;

    private Instant birthday;

    private String avatar;

    private String address;

    private String activeCode;

    @Enumerated(EnumType.STRING)
    private EStatusAccount status;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<ERole> roles;
}
