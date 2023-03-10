package com.example.quanlybanhang.controller;

import com.example.quanlybanhang.model.dto.ProductDTO;
import com.example.quanlybanhang.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;

    @GetMapping("/search")
    public ResponseEntity<Page<ProductDTO>> search(@RequestParam(required = false) String key,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "5") int size,
                                                   @RequestParam(defaultValue = "name") String[] properties,
                                                   @RequestParam(defaultValue = "ASC") Sort.Direction sort) {
        log.warn("[CONTROLLER] - SEARCH REQUEST: key = {}, page = {}, size = {}, properties = {}, sort = {}",
                key, page, size, properties, sort);
        return ResponseEntity.ok(productService.search(key, page, size, properties, sort));
    }

    @GetMapping("/products")
    public ResponseEntity<Page<ProductDTO>> getAll(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "5") int size,
                                                   @RequestParam(defaultValue = "name") String[] properties,
                                                   @RequestParam(defaultValue = "ASC") Sort.Direction sort) {
        log.warn("[CONTROLLER] - GET ALL PRODUCT REQUEST");
        return ResponseEntity.ok(productService.search(null, page, size, properties, sort));
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Long id) {
        log.warn("[CONTROLLER] - GET PRODUCT BY ID: " + id);
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping("/management/products")
    @ResponseStatus(HttpStatus.CREATED)
    public void insertProduct(@RequestBody @Valid ProductDTO productDTO) {
        log.warn("[CONTROLLER] - INSERT PRODUCT: " + productDTO);
        productService.insertProduct(productDTO);
    }


    @PatchMapping("management/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        log.warn("[CONTROLLER] - UPDATE PRODUCT: " + productDTO);
        productService.updateProduct(id, productDTO);
    }

    @DeleteMapping("/management/products")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@RequestParam Long[] ids) {
        log.warn("[CONTROLLER] - DELETE PRODUCT BY ID: " + Arrays.toString(ids));
        productService.deleteProduct(ids);
    }
}
