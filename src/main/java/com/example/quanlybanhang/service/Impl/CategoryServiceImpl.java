package com.example.quanlybanhang.service.Impl;

import com.example.quanlybanhang.model.dtos.CategoryDTO;
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

    @Override
    public List<CategoryDTO> getAllCategoryByUser() {
        return null;
    }

    @Override
    public Page<CategoryDTO> getAllCategoryByAdmin() {
        return null;
    }

    @Override
    public void insertCategory(CategoryDTO categoryDTO) {

    }

    @Override
    public void updateCategory(Long id, CategoryDTO categoryDTO) {

    }

    @Override
    public void deleteCategory(Long id) {

    }
}
