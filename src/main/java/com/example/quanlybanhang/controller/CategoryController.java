package com.example.quanlybanhang.controller;

import com.example.quanlybanhang.model.dto.CategoryDTO;
import com.example.quanlybanhang.service.Impl.CategoryServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
@AllArgsConstructor
@Slf4j
public class CategoryController {
    private final CategoryServiceImpl categoryServiceImpl;

    @GetMapping
    public ResponseEntity<Page<CategoryDTO>> getAllCategory() {
        return ResponseEntity.ok(categoryServiceImpl.getAllCategory());
    }

    @PostMapping
    public void insertCategory(@RequestBody CategoryDTO categoryDTO) {
        categoryServiceImpl.createCategory(categoryDTO);
    }
}
