//package vn.techmaster.usermanagement;
//
//import com.github.javafaker.Faker;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.annotation.Rollback;
//import vn.techmaster.usermanagement.entity.User;
//import vn.techmaster.usermanagement.repository.UserRepository;
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class UserRepoTest {
//    @Autowired
//    private UserRepository userRepositoryJPA;
//
//    @Test
//    @Rollback(value = false)
//    void saveUser(){
//        Faker faker = new Faker();
//        List<User> users = new ArrayList<>();
//        for (int i = 0; i < 30; i++) {
//            User user = User.builder()
//                    .withName(faker.name().fullName())
//                    .withEmail(faker.internet().emailAddress())
//                    .withPassword("12345")
//                    .build();
//            users.add(user);
//        }
//        userRepositoryJPA.saveAll(users);
//    }
//    @Test
//    void getAllUser(){
//        List<User> users = userRepositoryJPA.findAll();
//        assertThat(users.size()).isEqualTo(30);
//    }
//
//}
