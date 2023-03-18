package com.example.quanlybanhang.model.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @NotEmpty
    private String name;

    private String code;

    private String description;

    @NotNull
    private Double price;

    @NotNull
    private Double finalPrice;

    private Integer quantity;

    private Double rate;

    @Column(name = "is_available", columnDefinition = "boolean default false")
    private boolean isAvailable;

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
