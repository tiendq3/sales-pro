package com.example.quanlybanhang.service;

import com.example.quanlybanhang.model.request.OrderRequest;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    void createOrder(OrderRequest order);
}
