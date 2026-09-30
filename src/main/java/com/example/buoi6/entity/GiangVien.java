package com.example.buoi6.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "giang_vien")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GiangVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mssv")
    private String mssv;

    @Column(name = "ten")
    private String ten;

    @Column(name = "tuoi")
    private Long tuoi;

    @Column(name = "gioi_tinh")
    private Boolean gioiTinh;   // true = Nam, false = Nữ

    @Column(name = "que_quan")
    private String queQuan;
}