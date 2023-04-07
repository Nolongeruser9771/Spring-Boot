package com.example.demologin.services;

import com.example.demologin.entity.User;
import com.example.demologin.models.ReqLogin;
import com.example.demologin.models.UserDTO;
import com.example.demologin.models.UserMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserServiceImpl implements UserService{
    //Tạo mảng động thay cho database

    private static ArrayList<User> users = new ArrayList<User>();

    static {
        users.add(new User(1, "MongMo", "mongmo@gmail.com","0987654321","avatar.img"));
        users.add(new User(2, "NhuLac", "laclac@gmail.com","0123456789","avatar1.img"));
        users.add(new User(3, "LongLeo", "longleo@gmail.com","0987564664","avatar3.img"));
        users.add(new User(4, "BanhTeo", "teo@gmail.com","0874845455","avatar9.img"));
    }

    @Override
    public List<UserDTO> getUserList() {
        List<UserDTO> userDtos = new ArrayList<>();

        for (User user: users) {
            userDtos.add(UserMapper.touserDto(user));
        }
        return userDtos;
    }

    @Override
    public UserDTO getUserByLoginInfo(ReqLogin req) {
        for (User user: users) {
            if (user.getUsername().equals(req.getUsername()) && user.getPassword().equals(req.getPassword())) {
                return UserMapper.touserDto(user);
            }
        }
        return null;
    }
}
