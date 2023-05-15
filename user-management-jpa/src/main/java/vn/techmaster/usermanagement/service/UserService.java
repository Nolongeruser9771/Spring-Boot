package vn.techmaster.usermanagement.service;

import org.springframework.web.multipart.MultipartFile;
import vn.techmaster.usermanagement.dto.CreateUserRequest;
import vn.techmaster.usermanagement.dto.UpdateUserPasswordRequest;
import vn.techmaster.usermanagement.dto.UpdateUserRequest;
import vn.techmaster.usermanagement.dto.UserDTO;
import vn.techmaster.usermanagement.entity.User;
import vn.techmaster.usermanagement.model.FileResponse;

import java.util.List;

public interface UserService {
    List<UserDTO> findAll();

    List<UserDTO> findUsersByName(String name);

    UserDTO findUserById(Integer id);

    UserDTO addUser(CreateUserRequest userRequest);

    UserDTO updateUser(Integer id, UpdateUserRequest userRequest);

    void deleteUser(Integer id);

    void updateUserPassWord(Integer id, UpdateUserPasswordRequest updateUserPasswordRequest);

    FileResponse updateUserAvatar(Integer id, MultipartFile file);
}
