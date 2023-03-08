package com.example.quanlybanhang.controller;

import com.example.quanlybanhang.model.dto.ProductDTO;
import com.example.quanlybanhang.service.Impl.ProductServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Slf4j
public class ProductController {
    private final ProductServiceImpl productServiceImpl;

    @GetMapping("/products/search")
    public ResponseEntity<Page<ProductDTO>> search(@RequestParam(required = false) String key,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "5") int size,
                                                   @RequestParam(defaultValue = "name") String[] properties,
                                                   @RequestParam(defaultValue = "ASC") Sort.Direction sort) {
        log.info("SEARCH");
        return ResponseEntity.ok(productServiceImpl.search(key, page, size, properties, sort));
    }

    @GetMapping("/products")
    public ResponseEntity<Page<ProductDTO>> getAll(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "5") int size,
                                                   @RequestParam(defaultValue = "name") String[] properties,
                                                   @RequestParam(defaultValue = "ASC") Sort.Direction sort) {
        log.info("GET ALL PRODUCT REQUEST");
        return ResponseEntity.ok(productServiceImpl.search(null, page, size, properties, sort));
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productServiceImpl.getProductById(id));
    }

    @PostMapping("/management/products")
    @ResponseStatus(HttpStatus.CREATED)
    public void insertProduct(@RequestBody @Valid ProductDTO productDTO) {
        log.info("INSERT NEW PRODUCT " + productDTO);
        productServiceImpl.insertProduct(productDTO);
    }


    @PutMapping("management/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        log.info("UPDATE PRODUCT " + productDTO);
        productServiceImpl.updateProduct(id, productDTO);
    }

    @DeleteMapping("/management/products")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@RequestParam Long[] ids) {
        productServiceImpl.deleteProduct(ids);
    }
}
