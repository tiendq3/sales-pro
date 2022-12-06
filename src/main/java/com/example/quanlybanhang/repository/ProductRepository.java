package com.example.quanlybanhang.repository;

import com.example.quanlybanhang.entity.Product;
import com.example.quanlybanhang.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM products p WHERE p.name like %:name1%  ", nativeQuery = true)
    List<Product> getProductByName(@Param("name1") String name1);

    @Query(
            value = "select  * from products p join categories c on p.category_id = c.id where c.name like %:name%",
            nativeQuery = true)
    List<Product> getProductByCategory(@Param("name") String name);
}
