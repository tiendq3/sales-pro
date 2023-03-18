package com.example.quanlybanhang.service.Impl;

import com.example.quanlybanhang.model.dtos.SupplierDTO;
import com.example.quanlybanhang.model.dtos.VoucherDTO;
import com.example.quanlybanhang.repository.SupplierRepository;
import com.example.quanlybanhang.service.SupplierService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Override
    public Page<SupplierDTO> getAllSupplier() {
        return null;
    }

    @Override
    public VoucherDTO getSupplierById(Long id) {
        return null;
    }

    @Override
    public void insertSupplier(SupplierDTO supplierDTO) {

    }

    @Override
    public void updateSupplier(Long id, SupplierDTO supplierDTO) {

    }

    @Override
    public void deleteSupplier(Long id) {

    }
}
