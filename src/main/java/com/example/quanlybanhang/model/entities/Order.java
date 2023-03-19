package com.example.quanlybanhang.model.entities;

import com.example.quanlybanhang.model.enums.EStatusOrder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;
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

//    @ManyToMany
//    private List<OrderItem> orderItems;

    @ElementCollection
    private Map<Product, Integer> items;

    @NotNull
    private Double totalCost;

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

    @ManyToOne
    @JsonIgnore
    private User user;
}
