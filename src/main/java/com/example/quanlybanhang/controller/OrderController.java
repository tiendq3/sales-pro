package com.example.quanlybanhang.controller;

import com.example.quanlybanhang.model.entities.Order;
import com.example.quanlybanhang.model.enums.EStatusOrder;
import com.example.quanlybanhang.model.request.OrderRequest;
import com.example.quanlybanhang.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<Page<Order>> getAllOrderByAdmin(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "30") int size,
            @RequestParam(defaultValue = "id") String[] properties,
            @RequestParam(defaultValue = "ASC") Sort.Direction sort) {
        log.warn("[CONTROLLER] - GET ALL ORDER");
        return ResponseEntity.ok(orderService.getAllOrder(page, size, properties, sort));
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        log.warn("[CONTROLLER] - GET ORDER BY ID: " + id);
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody @Valid OrderRequest orderRequest) {
        log.warn("[CONTROLLER] - CREATE ORDER: " + orderRequest);
        orderService.createOrder(orderRequest);
    }

    @PatchMapping("/orders/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOrderByUser(@PathVariable Long id, @RequestBody OrderRequest orderRequest) {
        log.warn("[CONTROLLER] - USER UPDATE ORDER: " + id);
        orderService.updateOrderByUser(id, orderRequest);
    }

    @PatchMapping("/orders/management/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOrderByAdmin(@PathVariable Long id, @RequestParam EStatusOrder status) {
        log.warn("[CONTROLLER] - ADMIN UPDATE ORDER: " + id);
        orderService.updateOrderByAdmin(id, status);
    }

    @DeleteMapping("/management/orders/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
