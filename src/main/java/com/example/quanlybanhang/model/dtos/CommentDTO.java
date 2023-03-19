package com.example.quanlybanhang.model.dtos;

import com.example.quanlybanhang.model.entities.Product;
import com.example.quanlybanhang.model.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private String comment;

    private Integer rating;

    private User user;

    private Product product;
}
