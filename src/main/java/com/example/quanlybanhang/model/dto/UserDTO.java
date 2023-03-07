package com.example.quanlybanhang.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String username;

    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password is required")
    @Size(min = 4, max = 20, message = "Password must be between 4 and 20 characters")
    private String password;

    @NotNull(message = "Email is required")
    @NotEmpty(message = "Email is required")
    @Email(message = "Please provide a valid email")
    private String email;

    private String lastName;

    private String firstName;



    private String address;
}
