package com.example.quanlybanhang.repository;

import com.example.quanlybanhang.model.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Voucher,Long> {
}
