package com.example.quanlybanhang.repository;

import com.example.quanlybanhang.model.entities.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher,Long> {
}
