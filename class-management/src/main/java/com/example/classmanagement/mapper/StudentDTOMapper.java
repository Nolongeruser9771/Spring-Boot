package com.example.classmanagement.mapper;

import com.example.classmanagement.dto.StudentDTO;
import com.example.classmanagement.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentDTOMapper {
    public static StudentDTO toStudentDTO(Student student){
        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getAge(),
                student.getRanking(),
                student.getClazz()==null? null: student.getClazz().getId()
        );
    }
}
