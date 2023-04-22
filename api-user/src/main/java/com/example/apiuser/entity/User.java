package com.example.apiuser.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    static Integer AUTO_ID = 0;
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String avatar;

    public User(String name, String email, String phone, String avatar) {
        AUTO_ID++;
        this.id = AUTO_ID;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
    }
}
