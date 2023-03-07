package com.example.quanlybanhang.controller;

import com.example.quanlybanhang.model.entity.Category;
import com.example.quanlybanhang.model.dto.CategoryDTO;
import com.example.quanlybanhang.service.Impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/categories",method = RequestMethod.GET)
    public List<Category> getAllCategory(){
        return categoryService.getAllCategory();
    }

    @RequestMapping(
            value = "/categories",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createCategory(@RequestBody() CategoryDTO categoryDTO){
        categoryService.createCategory(categoryDTO);
        return ResponseEntity.ok().body("them thanh cong");
    }
}
