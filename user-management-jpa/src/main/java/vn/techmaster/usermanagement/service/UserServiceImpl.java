package vn.techmaster.usermanagement.service;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.techmaster.usermanagement.dto.CreateUserRequest;
import vn.techmaster.usermanagement.dto.UpdateUserPasswordRequest;
import vn.techmaster.usermanagement.dto.UpdateUserRequest;
import vn.techmaster.usermanagement.dto.UserDTO;
import vn.techmaster.usermanagement.entity.User;
import vn.techmaster.usermanagement.exception.NotFoundException;
import vn.techmaster.usermanagement.exception.UserHandleException;
import vn.techmaster.usermanagement.mapper.UserMapper;
import vn.techmaster.usermanagement.model.FileResponse;
import vn.techmaster.usermanagement.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(UserMapper::toUserDTO).toList();
    }

    @Override
    public List<UserDTO> findUsersByName(String name) {
        return userRepository.findUsersByName(name.trim()).stream().map(UserMapper::toUserDTO).toList();
    }

    @Override
    public UserDTO findUserById(Integer id) {
        return UserMapper.toUserDTO(userRepository.findUserById(id));
    }

    @Override
    public UserDTO addUser(CreateUserRequest userRequest) {
        User user = userRepository.findUserByEmail(userRequest.email());
        if (user!=null){
            throw new UserHandleException("Email da ton tai!");
        }
        User newUser =new User(userRequest.name(),
                userRequest.email(),
                userRequest.phone(),
                userRequest.address(),
                userRequest.avatar(),
                userRequest.password());
        userRepository.save(newUser);
        return UserMapper.toUserDTO(newUser);
    }

    @Override
    public UserDTO updateUser(Integer id, UpdateUserRequest userRequest) {
        User user2update = userRepository.findUserById(id);
        if (user2update==null){
            throw new NotFoundException("User id "+ id + " not found");
        }
        user2update.setName(userRequest.name());
        user2update.setEmail(userRequest.email());
        user2update.setPhone(userRequest.phone()!=null? userRequest.phone(): user2update.getPhone());
        userRepository.save(user2update);
        return UserMapper.toUserDTO(user2update);
    }

    @Override
    public void deleteUser(Integer id) {
        User user2delete = userRepository.findUserById(id);
        if (user2delete==null){
            throw new NotFoundException("User id "+ id + " not found");
        }
        userRepository.delete(user2delete);
    }

    @Override
    public void updateUserPassWord(Integer id, UpdateUserPasswordRequest updateUserPasswordRequest) {
        User user2update = userRepository.findUserById(id);
        if (user2update==null){
            throw new NotFoundException("User id "+ id+" not found");
        }
        //check lai Bcrypt trong database
        if (!BCrypt.checkpw(updateUserPasswordRequest.oldPassword(), user2update.getPassword())) {
            throw new UserHandleException("Password not match");
        }
        user2update.setPassword(BCrypt.hashpw(updateUserPasswordRequest.newPassword(),BCrypt.gensalt(12)));
        userRepository.save(user2update);
    }

    @Override
    public FileResponse updateUserAvatar(Integer id, MultipartFile file) {
        return null;
    }
}
