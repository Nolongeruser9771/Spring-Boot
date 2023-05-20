package vn.techmaster.usermanagement.repository;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.usermanagement.entity.User;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAll();

    User findUserById(Integer id);

    Page<User> findAll(Pageable pageable);

    List<User> findByNameContainsOrEmailContains(String name, String email);

    List<User> findUsersByNameBefore(String nameSearch);

    List<User> findUsersByNameIsLike(String nameSearch);

    boolean existsUserByEmail(String email);

    User findUserByEmail(@NotEmpty(message = "Email không được để trống") @Email String email);
}
