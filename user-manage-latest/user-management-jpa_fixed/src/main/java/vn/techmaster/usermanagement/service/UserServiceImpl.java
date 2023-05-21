package vn.techmaster.usermanagement.service;

import jakarta.transaction.Transactional;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.techmaster.usermanagement.dto.*;
import vn.techmaster.usermanagement.entity.User;
import vn.techmaster.usermanagement.exception.NotFoundException;
import vn.techmaster.usermanagement.exception.UserHandleException;
import vn.techmaster.usermanagement.mapper.UserMapper;
import vn.techmaster.usermanagement.model.FileResponse;
import vn.techmaster.usermanagement.repository.UserRepository;

import java.util.*;
import java.util.function.Predicate;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserDTO> findAll() {
        if (userRepository.findAll().size()==0) {
            return new ArrayList<>();
        }
        return userRepository.findAll().stream().map(UserMapper::toUserDTO).toList();
    }

    @Override
    public UserDTO findUserById(Integer id) {
        if (userRepository.findUserById(id)==null) {
            throw new NotFoundException("User id "+id+" not found!");
        }
        return UserMapper.toUserDTO(userRepository.findUserById(id));
    }

    @Override
    public UserDTO addUser(CreateUserRequest userRequest) {
        List<User> users = userRepository.findAll();
        for (User user: users){
            if (user.getEmail().equals(userRequest.email())) {
                throw new UserHandleException("Email da ton tai!");
            }
        }
        User newUser =new User(userRequest.name(),
                userRequest.email(),
                userRequest.phone(),
                userRequest.address(),
                userRequest.avatar()!=null? userRequest.avatar(): "default-avatar.jpg",
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

    @Transactional
    @Override
    public void testTransaction() {
        // User hợp lệ
        User user1 = new User();
        user1.setEmail("mongmo@gmail.com");
        user1.setName("Nguyễn Thị Mộng Mơ");
        user1.setPassword("123456789");
        user1.setPhone("0916125984");
//        user1.setRole("USER");

        // User không hợp lệ
        User user2 = new User();
        user2.setEmail("lunglinh@gmail.com");
        user2.setName("Phan Thị Lung Linh");
        user2.setPassword("abc123");
        user2.setPhone("098765432100000");
//        user2.setRole("USER");

        userRepository.save(user1);
        userRepository.save(user2);
    }

    @Override
    public Page<UserDTO> pageDivideByPara(Integer pageNo, Integer pageSize, String searchValue, String sortField1, String sortField2) {
        //split to get Direction + properties => add to sort List;
        List<String> sortFields = new ArrayList<>(Arrays.asList(sortField1, sortField2));
        List<Sort.Order> sorts = findSortList(sortFields);

        return userRepository.findAllByNameContainsOrEmailContains(searchValue, searchValue,PageRequest.of(pageNo, pageSize, Sort.by(sorts)));
    }

    //find sort properties and direction
    private List<Sort.Order> findSortList(List<String> sortFields) {
        List<Sort.Order> sorts = new ArrayList<>();
        for (String sortField: sortFields){
            String field = sortField.split(",")[0];
            String order = sortField.split(",")[1];

            if(order.equalsIgnoreCase("desc")){
                sorts.add(new Sort.Order(Sort.Direction.DESC, field));
                break;
            }
            sorts.add(new Sort.Order(Sort.Direction.ASC, field));
        }
        return sorts;
    }


}
