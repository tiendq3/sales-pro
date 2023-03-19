package com.example.quanlybanhang.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    @NotEmpty
    private String comment;

    @NotNull
    private Integer rating;

    @NotNull
    private UserDTO userDTO;

    @NotNull
    private ProductDTO productDTO;
}
