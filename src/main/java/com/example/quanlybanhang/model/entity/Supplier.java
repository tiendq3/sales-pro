package com.example.quanlybanhang.model.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "suppliers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Value("${spring.secret}")
    private String name;

    private String description;

    @ManyToMany
    @JoinTable(name = "product_supplier",
            joinColumns = {@JoinColumn(name = "supplier_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "product_id", nullable = false, updatable = false)})
    private Set<Product> products;
}
