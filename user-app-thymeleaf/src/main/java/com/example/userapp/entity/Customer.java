package com.example.userapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    //cascade: hành động cha ảnh hưởng tới con
    //orphanRemoval: tự thực hiện update csdl khi chạy lệnh

    //FetchType
    //LAZY: Khi nào sử dụng mới qurey, mặc định khi có Many đằng sau
    //EAGER: Query luôn dữ liệu liên quan, mặc định khi có One đằng sau
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<>();

}