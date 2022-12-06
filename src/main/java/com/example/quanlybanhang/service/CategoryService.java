package com.example.quanlybanhang.service;

import com.example.quanlybanhang.entity.Category;
import com.example.quanlybanhang.model.CategoryModel;
import com.example.quanlybanhang.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }
    public void createCategory(CategoryModel categoryModel){
        Category category = modelMapper.map(categoryModel,Category.class);
        categoryRepository.save(category);
    }
}
