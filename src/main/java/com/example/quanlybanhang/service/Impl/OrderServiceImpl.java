package com.example.quanlybanhang.service.Impl;

import com.example.quanlybanhang.exception.BadRequestException;
import com.example.quanlybanhang.exception.NotFoundException;
import com.example.quanlybanhang.exception.UnAuthorityException;
import com.example.quanlybanhang.model.entities.Order;
import com.example.quanlybanhang.model.entities.Product;
import com.example.quanlybanhang.model.entities.User;
import com.example.quanlybanhang.model.enums.ERole;
import com.example.quanlybanhang.model.enums.EStatusOrder;
import com.example.quanlybanhang.model.request.OrderItem;
import com.example.quanlybanhang.model.request.OrderRequest;
import com.example.quanlybanhang.repository.OrderRepository;
import com.example.quanlybanhang.repository.ProductRepository;
import com.example.quanlybanhang.repository.UserRepository;
import com.example.quanlybanhang.service.OrderService;
import com.example.quanlybanhang.service.other.MailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    private final MailService mailService;

    private final UserRepository userRepository;

    @Override
    public Order getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) throw new NotFoundException("not found order by code: " + id);
        return order;
    }

    @Override
    public Page<Order> getAllOrder(int page, int size, String[] properties, Sort.Direction sort) {
        Pageable pageable = PageRequest.of(page, size, sort, properties);
        Authentication authentication = SecurityContextHolder.createEmptyContext().getAuthentication();
        // check authentication = null?
        if (authentication == null) throw new UnAuthorityException("Un Authorities");
        String email = authentication.getName();
        User user = userRepository.findUserByEmail(email);
        if (user == null) throw new UnAuthorityException("Un Authorities");
        if (user.getRoles().contains(ERole.ADMIN))
            return orderRepository.findAll(pageable);
        return orderRepository.findByEmail(user.getEmail(), pageable);
    }

    @Transactional
    @Override
    public void createOrder(OrderRequest order) {
        log.warn("[SERVICE] - CREATE ORDER: " + order);
        Map<Product, Integer> orderItems = new HashMap<>();
        double totalCost = 0;

        List<OrderItem> orderItemList = order.getOrderItems();

        for (OrderItem orderItem : orderItemList) {
            Product product = productRepository.findById(orderItem.getProductDTO().getId()).orElse(null);
            if (product == null || product.getQuantity() < orderItem.getAmount())
                throw new NotFoundException("not found product " + orderItem.getProductDTO().getName() + ", or exceed the number of products in stock");

            totalCost += product.getFinalPrice() * orderItem.getAmount();
            if (orderItems.containsKey(product))
                orderItems.put(product, orderItems.get(product) + orderItem.getAmount());
            else orderItems.put(product, orderItem.getAmount());
            product.setQuantity(product.getQuantity() - orderItem.getAmount());
            productRepository.save(product);
        }

        Order newOrder = Order
                .builder()
                .items(orderItems)
                .totalCost(totalCost)
                .name(order.getName())
                .email(order.getEmail())
                .phone(order.getPhone())
                .address(order.getAddress())
                .statusOrder(EStatusOrder.PENDING_CONFIRMATION)
                .build();
        mailService.sendOrderInfo(newOrder);
        orderRepository.save(newOrder);
    }

    @Override
    public void updateOrderByAdmin(Long id, EStatusOrder status) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) throw new NotFoundException("not found order by code: " + id);
        order.setStatusOrder(status);
        orderRepository.save(order);
    }

    @Override
    public void updateOrderByUser(Long id, OrderRequest orderRequest) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) throw new NotFoundException("not found order by code: " + id);
        if (order.getStatusOrder() != EStatusOrder.PENDING_CONFIRMATION ||
                Instant.now().getEpochSecond() - order.getCreatedAt().getEpochSecond() > 86400)
            throw new BadRequestException("Orders can only be canceled if they are in pending confirmation and no later than 1 day after placing the order");
        if (orderRequest.getStatusOrder() == EStatusOrder.CANCELLED) order.setStatusOrder(EStatusOrder.CANCELLED);
        order.setName(orderRequest.getName());
        order.setEmail(orderRequest.getEmail());
        order.setAddress(orderRequest.getAddress());
        order.setPhone(orderRequest.getPhone());
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) throw new NotFoundException("not found order by id: " + id);
        orderRepository.delete(order);
    }


//    @Transactional
//    @Override
//    public void createOrder(OrderRequest order) {
//        log.warn("[SERVICE] - CREATE ORDER: " + order);
//        Set<ProductDTO> productDTOSet = order.getOrderItems().keySet();
//        HashMap<Product, Integer> productIntegerHashMap = new HashMap<>();
//        double totalPrice = 0;
//        for (ProductDTO productDTO : productDTOSet) {
//            totalPrice += productDTO.getPrice() * order.getOrderItems().get(productDTO);
//            Product product = productRepository.findById(productDTO.getId()).orElse(null);
//            if (product == null || product.getQuantity() < order.getOrderItems().get(productDTO))
//                throw new NotFoundException("not found product " + productDTO.getName() + ", or exceed the number of products in stock");
//            productIntegerHashMap.put(product, order.getOrderItems().get(productDTO));
//            product.setQuantity(product.getQuantity() - order.getOrderItems().get(productDTO));
//            productRepository.save(product);
//        }
//        Order newOrder = Order
//                .builder()
//                .orderItems(productIntegerHashMap)
//                .totalPrice(totalPrice)
//                .name(order.getName())
//                .email(order.getPhone())
//                .address(order.getAddress())
//                .statusOrder(EStatusOrder.ORDER)
//                .build();
//        orderRepository.save(newOrder);
//    }
}
