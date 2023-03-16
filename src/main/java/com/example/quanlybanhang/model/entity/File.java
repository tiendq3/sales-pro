package com.example.quanlybanhang.model.entity;

import com.example.quanlybanhang.model.enums.EFileType;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "files")
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

    @Enumerated(EnumType.STRING)
    private EFileType type;

    private String ext;

    private Double size;

    private byte[] data;
}
