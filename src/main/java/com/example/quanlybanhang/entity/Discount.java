package com.example.quanlybanhang.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="discounts")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private int amount;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
