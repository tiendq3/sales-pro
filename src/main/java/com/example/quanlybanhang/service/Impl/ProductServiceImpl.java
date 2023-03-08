package com.example.quanlybanhang.service.Impl;

import com.example.quanlybanhang.exception.NotFoundException;
import com.example.quanlybanhang.model.dto.ProductDTO;
import com.example.quanlybanhang.model.entity.Product;
import com.example.quanlybanhang.repository.ProductRepository;
import com.example.quanlybanhang.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    @Override
    public Page<ProductDTO> search(String key, int pageNumber, int sizePage, String[] properties, Sort.Direction sort) {
        Pageable pageable = PageRequest.of(pageNumber, sizePage, Sort.Direction.ASC, properties);
        if (sort.isDescending()) {
            pageable = PageRequest.of(pageNumber, sizePage, Sort.Direction.DESC, properties);
        }
        Page<Product> products = productRepository.searchBy(key, pageable);
        return products.map(product -> modelMapper.map(product, ProductDTO.class));
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) throw new NotFoundException("Not found product by " + id);
        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public void insertProduct(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        product.setRate(0d);
        product.setAvailable(false);
        product.setCreatedAt(Instant.now());
        product.setUpdatedAt(new Date());
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) throw new NotFoundException("not found product by " + id);
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setFiles(productDTO.getFiles());
        product.setPrice(productDTO.getPrice());
        product.setFinalPrice(productDTO.getFinalPrice());
        product.setUpdatedAt(new Date());
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long[] ids) {
        for (Long id : ids) {
            Product product = productRepository.findById(id).orElse(null);
            if (product == null) throw new NotFoundException("not found product by " + id);
            productRepository.delete(product);
        }
    }
}
