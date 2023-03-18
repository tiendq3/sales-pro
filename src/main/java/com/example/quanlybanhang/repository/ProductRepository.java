package com.example.quanlybanhang.repository;

import com.example.quanlybanhang.model.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM products WHERE (:key IS NULL OR name LIKE CONCAT('%', :key, '%')) ",
            nativeQuery = true)
    Page<Product> searchBy(@Param("key") String key, Pageable pageable);
}
