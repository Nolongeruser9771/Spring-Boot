package com.example.demologin.services;

import com.example.demologin.entity.User;
import com.example.demologin.models.ReqLogin;
import com.example.demologin.models.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserDTO> getUserList();
    UserDTO getUserByLoginInfo(ReqLogin req);
}
