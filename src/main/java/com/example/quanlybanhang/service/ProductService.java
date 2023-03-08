package com.example.quanlybanhang.service;

import com.example.quanlybanhang.model.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface ProductService {

    Page<ProductDTO> search(String key, int pageNumber, int sizePage, String[] properties, Sort.Direction sort);

    ProductDTO getProductById(Long id);

    void insertProduct(ProductDTO productDTO);

    void updateProduct(Long id, ProductDTO productDTO);

    void deleteProduct(Long[] ids);
}
