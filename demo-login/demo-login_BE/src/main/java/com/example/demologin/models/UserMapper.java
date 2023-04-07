package com.example.demologin.models;

import com.example.demologin.entity.User;

public class UserMapper {
    //chuyển đổi object User -> userDto
    public static UserDTO touserDto(User user) {
        UserDTO userDto = new UserDTO();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setAvatar(user.getAvatar());
        
        return userDto;
    }
}
