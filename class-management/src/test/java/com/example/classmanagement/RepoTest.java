package com.example.classmanagement;

import com.example.classmanagement.entity.Clazz;
import com.example.classmanagement.entity.Student;
import com.example.classmanagement.repository.ClazzRepository;
import com.example.classmanagement.repository.StudentRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepoTest {

    @Autowired
    private ClazzRepository clazzRepository;

    @Autowired
    private StudentRepository studentRepository;


    @Test
    @Rollback(value = false)
    void saveClazz() {
        Clazz clazz = new Clazz();
        clazz.setAddress("los Angeles");
        clazzRepository.save(clazz);
    }

    @Test
    @Rollback(value = false)
    void saveStudents() {
        Faker faker = new Faker();
        for (int i = 0; i < 5; i++) {
            Student student = new Student().toBuilder()
                    .name(faker.name().fullName())
                    .age(faker.number().numberBetween(15,30))
                    .ranking("good")
                    .clazz(clazzRepository.findById(1).get())
                    .build();
            studentRepository.save(student);
        }
    }
}
