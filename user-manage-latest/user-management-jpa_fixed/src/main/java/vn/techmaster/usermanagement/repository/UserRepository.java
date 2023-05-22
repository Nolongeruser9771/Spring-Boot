package vn.techmaster.usermanagement.repository;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.usermanagement.dto.UserDTO;
import vn.techmaster.usermanagement.entity.User;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>, PagingAndSortingRepository<User,Integer> {
    List<User> findAll();

    User findUserById(Integer id);

    Page<User> findAll(Pageable pageable);

    Page<UserDTO> findAllByNameContainsOrEmailContains(String nameSearch, String emailSearch, Pageable pageable);

    boolean existsUserByEmail(String email);

    User findUserByEmail(@NotEmpty(message = "Email không được để trống") @Email String email);
}
