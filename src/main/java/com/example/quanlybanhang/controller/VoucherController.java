package com.example.quanlybanhang.controller;

import com.example.quanlybanhang.model.dtos.VoucherDTO;
import com.example.quanlybanhang.service.VoucherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1/vouchers")
public class VoucherController {

    private final VoucherService voucherService;

    @GetMapping("/{id}")
    public ResponseEntity<VoucherDTO> getVoucherById(@PathVariable Long id) {
        log.warn("[CONTROLLER] - GET VOUCHER BY ID: " + id);
        return ResponseEntity.ok(voucherService.getVoucherById(id));
    }

    @GetMapping
    public ResponseEntity<Page<VoucherDTO>> getAllVoucher() {
        log.warn("[CONTROLLER] - GET ALL VOUCHER");
        return ResponseEntity.ok(voucherService.getAllVoucher());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void insertVoucher(VoucherDTO voucherDTO) {
        log.warn("[CONTROLLER] - INSERT NEW VOUCHER: " + voucherDTO);
        voucherService.insertVoucher(voucherDTO);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateVoucher(Long id, VoucherDTO voucherDTO) {
        log.warn("[CONTROLLER] - UPDATE VOUCHER: " + voucherDTO);
        voucherService.updateVoucher(id, voucherDTO);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVoucher(Long id) {
        log.warn("[CONTROLLER] - DELETE VOUCHER BY ID:" + id);
        voucherService.deleteVoucher(id);
    }
}
