package com.example.weddingofhuongsan.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "gift")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Gift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "customerId")
    private int customerId;

    @NotNull
    @Column(name = "price")
    private double price;
}
