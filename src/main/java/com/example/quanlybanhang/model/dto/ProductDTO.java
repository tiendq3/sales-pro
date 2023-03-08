package com.example.quanlybanhang.model.dto;

import com.example.quanlybanhang.model.entity.File;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private Double finalPrice;

    private Set<File> files;
}
