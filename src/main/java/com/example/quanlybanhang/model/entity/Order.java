package com.example.quanlybanhang.model.entity;

import com.example.quanlybanhang.model.enums.EStatusOrder;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private Map<Product, Integer> orderItems;

    @NotEmpty
    private Double totalPrice;

    @NotEmpty
    private String name;

    @Email
    private String email;

    private String phone;

    @NotEmpty
    private String address;

    private Instant createdAt;

    @Enumerated(EnumType.STRING)
    private EStatusOrder statusOrder;
}
