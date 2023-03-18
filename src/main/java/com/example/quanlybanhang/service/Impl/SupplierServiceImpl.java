package com.example.quanlybanhang.service.Impl;

import com.example.quanlybanhang.exception.NotFoundException;
import com.example.quanlybanhang.model.entities.Supplier;
import com.example.quanlybanhang.repository.SupplierRepository;
import com.example.quanlybanhang.service.SupplierService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    private final ModelMapper modelMapper;

    @Override
    public Page<Supplier> getAllSupplier(int page, int size, String[] properties, Sort.Direction sort) {
        log.warn("[SERVICE] - GET ALL SUPPLIER");
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, properties);
        if (sort.isDescending()) {
            pageable = PageRequest.of(page, size, Sort.Direction.DESC, properties);
        }
        return supplierRepository.findAll(pageable);
    }

    @Override
    public Supplier getSupplierById(Long id) {
        log.warn("[SERVICE] - GET SUPPLIER BY ID: " + id);
        Supplier supplier = supplierRepository.findById(id).orElse(null);
        if (supplier == null) throw new NotFoundException("not found supplier by id: " + id);
        return modelMapper.map(supplier, Supplier.class);
    }

    @Override
    public void insertSupplier(Supplier supplier) {
        log.warn("[SERVICE] - INSERT NEW SUPPLIER");
        Supplier newSupplier = modelMapper.map(supplier, Supplier.class);
        supplierRepository.save(newSupplier);
    }

    @Override
    public void updateSupplier(Long id, Supplier newSupplier) {
        log.warn("[SERVICE] - UPDATE SUPPLIER");
        Supplier supplier = supplierRepository.findById(id).orElse(null);
        if (supplier == null) throw new NotFoundException("not found supplier by id: " + id);
        supplier.setName(newSupplier.getName());
        supplier.setDescription(newSupplier.getDescription());
        supplier.setAddress(newSupplier.getAddress());
        supplier.setPhone(newSupplier.getPhone());
        supplierRepository.save(supplier);
    }

    @Override
    public void deleteSupplier(Long id) {
        log.warn("[SERVICE] - DELETE SUPPLIER");
        Supplier supplier = supplierRepository.findById(id).orElse(null);
        if (supplier == null) throw new NotFoundException("not found supplier by id: " + id);
        supplierRepository.delete(supplier);
    }
}
