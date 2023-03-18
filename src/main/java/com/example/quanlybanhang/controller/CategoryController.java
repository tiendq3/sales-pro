package com.example.quanlybanhang.controller;

import com.example.quanlybanhang.model.dtos.CategoryDTO;
import com.example.quanlybanhang.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getAllCategoryByUser() {
        return ResponseEntity.ok(categoryService.getAllCategoryByUser());
    }

    @GetMapping("/management/categories")
    public ResponseEntity<Page<CategoryDTO>> getAllCategoryByAdmin() {
        return ResponseEntity.ok(categoryService.getAllCategoryByAdmin());
    }

    @PostMapping("/management/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public void insertCategory(@RequestBody CategoryDTO categoryDTO) {
        categoryService.insertCategory(categoryDTO);
    }

    @PatchMapping("/management/categories/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCategory(@PathVariable Long id, CategoryDTO categoryDTO) {
        categoryService.updateCategory(id, categoryDTO);
    }

    @DeleteMapping("/management/categories/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }

}
