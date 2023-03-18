package com.example.quanlybanhang.service;

import com.example.quanlybanhang.model.entities.Order;
import com.example.quanlybanhang.model.enums.EStatusOrder;
import com.example.quanlybanhang.model.request.OrderRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {


    Order getOrderById(Long id);

    Page<Order> getAllOrder(int page, int size, String[] properties, Sort.Direction sort);

    void createOrder(OrderRequest order);

    void updateOrderByAdmin(Long id, EStatusOrder status);

    void updateOrderByUser(Long id, OrderRequest orderRequest);

    void deleteOrder(Long id);
}
