package com.example.demologin.models;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ReqLogin {
    private String username;
    private String password;
}
