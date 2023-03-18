package com.example.quanlybanhang.model.request;

import com.example.quanlybanhang.model.dtos.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private ProductDTO productDTO;
    private Integer amount;
}
