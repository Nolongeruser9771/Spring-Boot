package vn.techmaster.usermanagement.service;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.techmaster.usermanagement.dto.*;
import vn.techmaster.usermanagement.entity.User;
import vn.techmaster.usermanagement.model.FileResponse;

import java.util.List;

@Service
public interface UserService {
    List<UserDTO> findAll();

    List<UserDTO> findByNameContainsOrEmailContains(String name, String email);

    UserDTO findUserById(Integer id);

    UserDTO addUser(CreateUserRequest userRequest);

    UserDTO updateUser(Integer id, UpdateUserRequest userRequest);

    void deleteUser(Integer id);

    void updateUserPassWord(Integer id, UpdateUserPasswordRequest updateUserPasswordRequest);

    FileResponse updateUserAvatar(Integer id, MultipartFile file);

    @Transactional
    public void testTransaction();

    List<UserDTO> pageDivideByPara(Integer pageNo, Integer pageSize, String sortField1, String sortField2);
}
