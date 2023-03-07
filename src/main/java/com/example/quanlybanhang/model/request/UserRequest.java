package com.example.quanlybanhang.model.request;

import com.example.quanlybanhang.config.AppConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String username;

    @Pattern(regexp = AppConstants.PASSWORD_REGEX,
            message = "Password must contain letters and numbers, at least 6 characters")
    private String password;

    private String lastName;

    private String firstName;

    private Integer age;
}
