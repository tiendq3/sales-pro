package com.example.quanlybanhang.service;

import com.example.quanlybanhang.model.entities.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public interface SupplierService {
    Page<Supplier> getAllSupplier(int page, int size, String[] properties, Sort.Direction sort);

    Supplier getSupplierById(Long id);

    void insertSupplier(Supplier supplier);

    void updateSupplier(Long id, Supplier supplier);

    void deleteSupplier(Long id);
}
