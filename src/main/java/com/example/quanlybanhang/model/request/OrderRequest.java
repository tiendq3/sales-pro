package com.example.quanlybanhang.model.request;

import com.example.quanlybanhang.config.AppConstants;
import com.example.quanlybanhang.model.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private Map<ProductDTO, Integer> orderItems;

    @NotEmpty(message = "Name cannot be left blank")
    private String name;

    @Email(message = "Please enter your email")
    private String email;

    @Pattern(regexp = AppConstants.PASSWORD_REGEX,
            message = "Please enter your phone")
    private String phone;

    @NotEmpty(message = "Please enter your address")
    private String address;
}
