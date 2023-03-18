package com.example.quanlybanhang.service.Impl;

import com.example.quanlybanhang.model.dtos.VoucherDTO;
import com.example.quanlybanhang.repository.VoucherRepository;
import com.example.quanlybanhang.service.VoucherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class VoucherServiceImpl implements VoucherService {

    private final VoucherRepository voucherRepository;

    @Override
    public Page<VoucherDTO> getAllVoucher() {
        return null;
    }

    @Override
    public VoucherDTO getVoucherById(Long id) {
        return null;
    }

    @Override
    public void insertVoucher(VoucherDTO voucherDTO) {

    }

    @Override
    public void updateVoucher(Long id, VoucherDTO voucherDTO) {

    }

    @Override
    public void deleteVoucher(Long id) {

    }
}
