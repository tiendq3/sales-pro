package com.example.quanlybanhang.service.Impl;

import com.example.quanlybanhang.model.dto.ProductDTO;
import com.example.quanlybanhang.model.entity.Product;
import com.example.quanlybanhang.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    public List<Product> getAllProductByAdmin() {
        return productRepository.findAll();
    }

    public List<ProductDTO> getAllProductByUser() {
        List<ProductDTO> productDTOS = productRepository
                .findAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
        for (int i = 0; i < productDTOS.size(); i++) {
            productDTOS.get(i).setCategoryName(getAllProductByAdmin().get(i).getCategory().getName());
        }
        return productDTOS;
    }

    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.equals(Optional.empty())) return null;
        return optionalProduct.get();
    }

    public List<Product> getProductByName(String name) {
        return productRepository.getProductByName(name);
    }

    public List<Product> getProductByCategory(String nameCategory) {
        return productRepository.getProductByCategory(nameCategory);
    }

    public boolean createProduct(Product product) {
        for (Product p : getAllProductByAdmin()) {
            if (p.getName().equals(product.getName()) && p.getCategory().getId() == product.getCategory().getId())
                return false;
        }
        productRepository.save(product);
        return true;
    }

    public boolean deleteProductById(Long id) {
        if (getProductById(id) == null) return false;
        productRepository.deleteById(id);
        return true;
    }

    public boolean updateProduct(Long id, Product product) {
        Product product1 = getProductById(id);
        if (product1 == null) return false;
        product1.setName(product.getName());
        product1.setDescription(product.getDescription());
        product1.setUpdatedAt(product.getUpdatedAt());
        product1.setCreatedAt(product.getCreatedAt());
        product1.setCategory(product.getCategory());
        productRepository.save(product1);
        return true;
    }
}
