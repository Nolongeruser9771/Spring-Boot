package vn.techmaster.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.usermanagement.dto.*;
import vn.techmaster.usermanagement.entity.User;
import vn.techmaster.usermanagement.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllUsers(){
        List<UserDTO> userList = userService.findAll();
        return ResponseEntity.ok().body(userList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Integer id){
        UserDTO user = userService.findUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/create-user")
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

    @GetMapping("")
    public ResponseEntity<?> findPageByPara(@RequestParam(value = "page", defaultValue = "0") Integer pageNo,
                                            @RequestParam(value = "size", defaultValue = "4") Integer pageSize,
                                            @RequestParam(value = "search", required = false) String search,
                                            @RequestParam(value = "sort", defaultValue = "id,asc", required = false) String sortField1,
                                            @RequestParam(value = "sort", defaultValue = "id,asc", required = false) String sortField2) {
        Page<UserDTO> page = userService.pageDivideByPara(pageNo,pageSize,search,sortField1,sortField2);
        return ResponseEntity.ok().body(page);
    }

//    UserDTO updateUser(Integer id, UpdateUserRequest userRequest);
//
//    void deleteUser(Integer id);
//
//    UserDTO updateUserPassWord(Integer id, UpdateUserPasswordRequest updateUserPasswordRequest);
    // TODO: write code for handle request
}
