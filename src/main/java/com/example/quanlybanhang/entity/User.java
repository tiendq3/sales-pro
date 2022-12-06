package com.example.quanlybanhang.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.type.StringNVarcharType;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.ConnectionBuilder;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @NotEmpty(message = "Thiếu username")
    private String password;
    private String last_name;
    private String first_name;
    private String gender;
    private String email;
    private Date birthday;
    private String avatar;
    private String phone;
    private String address;
    private String city;
    private String country;
    private String active_code;
    private String status;
    private Timestamp created_at;
    private Timestamp updated_at;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
    private Set<Role> roles;

}
