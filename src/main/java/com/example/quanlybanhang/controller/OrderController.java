package com.example.quanlybanhang.controller;

import com.example.quanlybanhang.model.request.OrderRequest;
import com.example.quanlybanhang.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody @Valid OrderRequest orderRequest) {
        log.warn("[CONTROLLER] - CREATE ORDER: " + orderRequest);
        orderService.createOrder(orderRequest);
    }
}
