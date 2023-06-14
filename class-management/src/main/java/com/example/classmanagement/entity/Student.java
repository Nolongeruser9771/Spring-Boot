package com.example.classmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "ranking")
    private String ranking;

    //Bảng đại diện phía n chứa id bảng 1 (ManyToOne)
    @ManyToOne
    @JoinColumn(name = "clazz_id")
    private Clazz clazz;
}
