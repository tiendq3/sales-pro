package com.example.quanlybanhang.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String path;

    private String type;

    private String ext;

    private Double size;
}
