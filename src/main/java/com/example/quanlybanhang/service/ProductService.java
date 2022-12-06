package com.example.quanlybanhang.service;

import com.example.quanlybanhang.entity.Product;
import com.example.quanlybanhang.model.ProductModel;
import com.example.quanlybanhang.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.sampled.Port;
import java.security.PublicKey;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<Product> getAllProductByAdmin() {
        return productRepository.findAll();
    }

    public List<ProductModel> getAllProductByUser() {
        List<ProductModel> productModels = productRepository
                .findAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductModel.class))
                .collect(Collectors.toList());
        for (int i = 0; i < productModels.size(); i++) {
            productModels.get(i).setCategory_name(getAllProductByAdmin().get(i).getCategory().getName());
        }
        return productModels;
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
        product1.setUpdated_at(product.getUpdated_at());
        product1.setCreated_at(product.getCreated_at());
        product1.setCategory(product.getCategory());
        productRepository.save(product1);
        return true;
    }
}
