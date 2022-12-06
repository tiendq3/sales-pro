package com.example.quanlybanhang.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Data
@Table(name = "permissions")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String permission_name;
    private Timestamp created_at;
    private Timestamp updated_at;

    @ManyToMany
    private Set<Role> roles;
}
