package com.example.quanlybanhang.service;

import com.example.quanlybanhang.model.dtos.VoucherDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface VoucherService {

    Page<VoucherDTO> getAllVoucher();

    VoucherDTO getVoucherById(Long id);

    void insertVoucher(VoucherDTO voucherDTO);

    void updateVoucher(Long id, VoucherDTO voucherDTO);

    void deleteVoucher(Long id);
}
