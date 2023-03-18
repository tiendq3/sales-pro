package com.example.quanlybanhang.service;

import com.example.quanlybanhang.model.dtos.CategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<CategoryDTO> getAllCategoryByUser();

    Page<CategoryDTO> getAllCategoryByAdmin();

    void insertCategory(CategoryDTO categoryDTO);

    void updateCategory(Long id, CategoryDTO categoryDTO);

    void deleteCategory(Long id);
}
