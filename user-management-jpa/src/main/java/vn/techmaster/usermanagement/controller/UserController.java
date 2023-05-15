package vn.techmaster.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.usermanagement.dto.CreateUserRequest;
import vn.techmaster.usermanagement.dto.UpdateUserPasswordRequest;
import vn.techmaster.usermanagement.dto.UpdateUserRequest;
import vn.techmaster.usermanagement.dto.UserDTO;
import vn.techmaster.usermanagement.entity.User;
import vn.techmaster.usermanagement.mapper.UserMapper;
import vn.techmaster.usermanagement.repository.UserRepository;
import vn.techmaster.usermanagement.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getAllUsers(){
        List<UserDTO> userList = userService.findAll();
        return ResponseEntity.ok().body(userList);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getUsersByNameContain(@RequestParam("name") String name){
        List<UserDTO> userList = userService.findUsersByName(name);
        return ResponseEntity.ok().body(userList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Integer id){
        UserDTO user = userService.findUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("create-user")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest userRequest){
        UserDTO newUser = userService.addUser(userRequest);
        return ResponseEntity.ok().body(newUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody UpdateUserRequest userRequest){
        UserDTO updatedUser = userService.updateUser(id, userRequest);
        return ResponseEntity.ok().body(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id){
        userService.deleteUser(id);
        return ResponseEntity.ok().body("Deleted user id "+ id + " successfully!");
    }

    @PutMapping("/update-pass/{id}")
    public ResponseEntity<?> updateUserPassword(@PathVariable Integer id, UpdateUserPasswordRequest updateUserPasswordRequest){
        userService.updateUserPassWord(id, updateUserPasswordRequest);
        return ResponseEntity.ok().body("Password updated successfully");
    }
//    UserDTO updateUser(Integer id, UpdateUserRequest userRequest);
//
//    void deleteUser(Integer id);
//
//    UserDTO updateUserPassWord(Integer id, UpdateUserPasswordRequest updateUserPasswordRequest);
    // TODO: write code for handle request
}
