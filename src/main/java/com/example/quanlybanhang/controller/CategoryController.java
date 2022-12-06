package com.example.quanlybanhang.controller;

import com.example.quanlybanhang.entity.Category;
import com.example.quanlybanhang.model.CategoryModel;
import com.example.quanlybanhang.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Category> getAllCategory(){
        return categoryService.getAllCategory();
    }

    @RequestMapping(
            value = "/create",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createCategory(@RequestBody CategoryModel categoryModel){
        categoryService.createCategory(categoryModel);
        return ResponseEntity.ok().body("them thanh cong");
    }
}
