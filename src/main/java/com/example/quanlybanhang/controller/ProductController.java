package com.example.quanlybanhang.controller;

import com.example.quanlybanhang.entity.Product;
import com.example.quanlybanhang.exceptionhandler.NotFoundException;
import com.example.quanlybanhang.model.ProductModel;
import com.example.quanlybanhang.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.objenesis.SpringObjenesis;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products/admin")
    public List<Product> getAllProductByAdmin() {
        return productService.getAllProductByAdmin();
    }

    @GetMapping("/products")
    public List<ProductModel> getAllProductByUser() {
        return productService.getAllProductByUser();
    }

    @RequestMapping(value = {"/products/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<String> getProductById(@RequestParam("id") Long id) {
//        if (productService.getProductById(id) == null) return ResponseEntity.ok().body("khong ton tai sp");
        if (productService.getProductById(id) == null) throw new NotFoundException("Not found product");
        return ResponseEntity.ok().body(productService.getProductById(id).toString());
    }

    @RequestMapping(value = {"/products/name"}, method = RequestMethod.GET)
    public ResponseEntity getProductByName(@RequestParam("name") String name){
        return ResponseEntity.ok().body(productService.getProductByName(name));
    }

    @RequestMapping(value = "/products/category",method = RequestMethod.GET)
    public ResponseEntity getProductByCategory(@RequestParam("nameCategory") String nameCategory){
        return ResponseEntity.ok().body(productService.getProductByCategory(nameCategory));
    }
    @RequestMapping(
            value = "/products",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createProduct(@RequestBody Product product) {
        if (!productService.createProduct(product)) return ResponseEntity.ok().body("da ton tai sp");
        return ResponseEntity.ok().body(product.toString());
    }

    @RequestMapping(
            value = "/products/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity updateProduct(@RequestParam("id") Long id, @RequestBody Product product) {
        if (!productService.updateProduct(id, product)) return ResponseEntity.ok().body("khong ton tai sp");
        return ResponseEntity.ok().body(product);
    }

    @RequestMapping(
            value = "/products/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product == null) return ResponseEntity.ok().body("khong ton tai sp");
        productService.deleteProductById(id);
        return ResponseEntity.ok().body(product);
    }

}
