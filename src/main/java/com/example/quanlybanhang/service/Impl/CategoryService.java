package com.example.quanlybanhang.service.Impl;

import com.example.quanlybanhang.model.dto.CategoryDTO;
import com.example.quanlybanhang.model.entity.Category;
import com.example.quanlybanhang.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    public void createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        categoryRepository.save(category);
    }
}
