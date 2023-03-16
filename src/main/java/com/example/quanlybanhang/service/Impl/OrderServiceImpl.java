package com.example.quanlybanhang.service.Impl;

import com.example.quanlybanhang.exception.NotFoundException;
import com.example.quanlybanhang.model.dto.ProductDTO;
import com.example.quanlybanhang.model.entity.Order;
import com.example.quanlybanhang.model.entity.Product;
import com.example.quanlybanhang.model.enums.EStatusOrder;
import com.example.quanlybanhang.model.request.OrderRequest;
import com.example.quanlybanhang.repository.OrderRepository;
import com.example.quanlybanhang.repository.ProductRepository;
import com.example.quanlybanhang.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    public Page<Order> getAllOrder() {
        return null;
    }

    @Transactional
    @Override
    public void createOrder(OrderRequest order) {
        log.warn("[SERVICE] - CREATE ORDER: " + order);
        Set<ProductDTO> productDTOSet = order.getOrderItems().keySet();
        HashMap<Product, Integer> productIntegerHashMap = new HashMap<>();
        double totalPrice = 0;
        for (ProductDTO productDTO : productDTOSet) {
            totalPrice += productDTO.getPrice() * order.getOrderItems().get(productDTO);
            Product product = productRepository.findById(productDTO.getId()).orElse(null);
            if (product == null || product.getQuantity() < order.getOrderItems().get(productDTO))
                throw new NotFoundException("not found product " + productDTO.getName() + ", or exceed the number of products in stock");
            productIntegerHashMap.put(product, order.getOrderItems().get(productDTO));
            product.setQuantity(product.getQuantity() - order.getOrderItems().get(productDTO));
            productRepository.save(product);
        }
        Order newOrder = Order
                .builder()
                .orderItems(productIntegerHashMap)
                .totalPrice(totalPrice)
                .name(order.getName())
                .email(order.getPhone())
                .address(order.getAddress())
                .statusOrder(EStatusOrder.ORDER)
                .build();
        orderRepository.save(newOrder);
    }
}
