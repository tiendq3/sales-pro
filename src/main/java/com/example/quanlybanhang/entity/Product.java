package com.example.quanlybanhang.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;

    @ManyToOne
    private Category category;
//    @Column(name = "category_id")
//    private Long category_id;

//    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp created_at;

//    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp updated_at;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "products")
    private Set<Supplier> supplierList =null;
}
