package vn.techmaster.usermanagement;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import vn.techmaster.usermanagement.entity.User;
import vn.techmaster.usermanagement.repository.UserRepository;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepoTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @Rollback(value = false)
    void saveUser(){
        Faker faker = new Faker();
        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            User user = User.builder()
                    .name(faker.name().fullName())
                    .email(faker.internet().emailAddress())
                    .phone(faker.phoneNumber().cellPhone())
                    .address(faker.address().city())
                    .password(faker.internet().password(8,20,true))
                    .avatar("default-avatar.jpg")
                    .build();
            users.add(user);
        }
        userRepository.saveAll(users);
    }
    @Test
    void getAllUser(){
        List<User> users = userRepository.findAll();
        assertThat(users.size()).isEqualTo(100);
    }

    //test giới hạn dữ liệu trả về ở đây
}
