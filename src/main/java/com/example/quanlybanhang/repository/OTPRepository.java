package com.example.quanlybanhang.repository;

import com.example.quanlybanhang.model.entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OTPRepository extends JpaRepository<OTP, Long> {
    OTP findByEmail(String email);
}
