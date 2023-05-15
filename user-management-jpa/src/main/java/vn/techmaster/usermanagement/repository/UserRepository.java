package vn.techmaster.usermanagement.repository;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.usermanagement.entity.User;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAll();

    User findUserById(Integer id);

    List<User> findUsersByName(String name);

    List<User> findUsersByNameBefore(String nameSearch);

    List<User> findUsersByNameIsLike(String nameSearch);

    boolean existsUserByEmail(String email);

    User findUserByEmail(@NotEmpty(message = "Email không được để trống") @Email String email);
}
