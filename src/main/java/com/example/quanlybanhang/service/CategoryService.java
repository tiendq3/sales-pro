package com.example.quanlybanhang.service;

import com.example.quanlybanhang.model.dto.CategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    Page<CategoryDTO> getAllCategory();

    void insertCategory(CategoryDTO categoryDTO);

    void updateCategory(Long id, CategoryDTO categoryDTO);

    void deleteCategory(Long id);
}
