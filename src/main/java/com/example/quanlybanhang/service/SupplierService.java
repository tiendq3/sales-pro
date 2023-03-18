package com.example.quanlybanhang.service;

import com.example.quanlybanhang.model.dtos.SupplierDTO;
import com.example.quanlybanhang.model.dtos.VoucherDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface SupplierService {

    Page<SupplierDTO> getAllSupplier();

    VoucherDTO getSupplierById(Long id);

    void insertSupplier(SupplierDTO supplierDTO);

    void updateSupplier(Long id, SupplierDTO supplierDTO);

    void deleteSupplier(Long id);
}
