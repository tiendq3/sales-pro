package com.example.quanlybanhang.model.dtos;

import com.example.quanlybanhang.model.entities.Category;
import com.example.quanlybanhang.model.entities.File;
import com.example.quanlybanhang.model.entities.Supplier;
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

    private Integer quantity;

    private Double price;

    private Double finalPrice;

    private Set<File> files;

    private Category category;

    private Supplier supplier;
}
