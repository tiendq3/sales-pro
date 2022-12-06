package com.example.quanlybanhang.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="categories")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
}
