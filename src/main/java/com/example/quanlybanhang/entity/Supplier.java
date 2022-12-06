package com.example.quanlybanhang.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;

    @ManyToMany
    @JoinTable(name = "product_supplier",
            joinColumns = { @JoinColumn(name = "supplier_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "product_id", nullable = false, updatable = false) })
    private Set<Product> products;
}
