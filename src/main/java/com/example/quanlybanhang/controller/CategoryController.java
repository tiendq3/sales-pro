package com.example.quanlybanhang.controller;

import com.example.quanlybanhang.model.entity.Category;
import com.example.quanlybanhang.model.dto.CategoryDTO;
import com.example.quanlybanhang.service.Impl.CategoryServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Slf4j
public class CategoryController {
    private final CategoryServiceImpl categoryServiceImpl;

    @RequestMapping(value = "/categories",method = RequestMethod.GET)
    public List<Category> getAllCategory(){
        return categoryServiceImpl.getAllCategory();
    }

    @RequestMapping(
            value = "/categories",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createCategory(@RequestBody() CategoryDTO categoryDTO){
        categoryServiceImpl.createCategory(categoryDTO);
        return ResponseEntity.ok().body("them thanh cong");
    }
}
