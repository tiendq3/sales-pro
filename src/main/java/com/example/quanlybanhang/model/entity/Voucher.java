package com.example.quanlybanhang.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "vouchers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double value;
}
