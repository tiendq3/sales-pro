package com.example.quanlybanhang.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Data
@Table(name ="roles" )
public class Role {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String role_name;
    private Timestamp created_at;
    private Timestamp updated_at;

    @ManyToMany
    @JoinTable(name = "role_has_permissions",
            joinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "permission_id", nullable = false, updatable = false) })
    private Set<Permission> permissions;

    @ManyToMany
    @JoinTable(name = "user_has_roles",
            joinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "user_id", nullable = false, updatable = false) })
    private Set<User> users;

}
