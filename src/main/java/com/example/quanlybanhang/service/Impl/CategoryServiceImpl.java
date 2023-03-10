package com.example.quanlybanhang.service.Impl;

import com.example.quanlybanhang.model.dto.CategoryDTO;
import com.example.quanlybanhang.model.entity.Category;
import com.example.quanlybanhang.repository.CategoryRepository;
import com.example.quanlybanhang.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    public Page<CategoryDTO> getAllCategory() {
        return null;
    }

    public void createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        categoryRepository.save(category);
    }
}
