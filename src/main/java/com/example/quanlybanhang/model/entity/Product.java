package com.example.quanlybanhang.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String code;

    private String description;

    private Double price;

    private Double finalPrice;

    private Integer quantity;

    private Double rate;

    private Boolean available;

    private Instant createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @ManyToMany
    private Set<File> files;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Supplier supplier;
}
