package com.example.quanlybanhang.controller;

import com.example.quanlybanhang.model.entities.Supplier;
import com.example.quanlybanhang.service.SupplierService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable Long id) {
        log.warn("[CONTROLLER] - USER GET ALL CATEGORY");
        return ResponseEntity.ok(supplierService.getSupplierById(id));
    }

    @GetMapping
    public ResponseEntity<Page<Supplier>> getAllSupplier(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "30") int size,
                                                         @RequestParam(defaultValue = "name") String[] properties,
                                                         @RequestParam(defaultValue = "ASC") Sort.Direction sort) {
        log.warn("[CONTROLLER] - GET ALL SUPPLIER");
        return ResponseEntity.ok(supplierService.getAllSupplier(page, size, properties, sort));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void insertSupplier(Supplier supplier) {
        log.warn("[CONTROLLER] - INSERT NEW SUPPLIER: " + supplier);
        supplierService.insertSupplier(supplier);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSupplier(Long id, Supplier supplier) {
        log.warn("[CONTROLLER] - UPDATE SUPPLIER: " + supplier);
        supplierService.updateSupplier(id, supplier);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSupplier(Long id) {
        log.warn("[CONTROLLER] - DELETE SUPPLIER BY ID: " + id);
        supplierService.deleteSupplier(id);
    }
}
