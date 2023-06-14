package com.example.classmanagement.mapper;

import com.example.classmanagement.dto.ClazzDTO;
import com.example.classmanagement.entity.Clazz;
import org.springframework.stereotype.Component;

@Component
public class ClazzDTOMapper {
    public static ClazzDTO toClazzDTO(Clazz clazz){
        return new ClazzDTO(
                clazz.getId(),
                clazz.getAddress(),
                clazz.getStudents().stream().map(StudentDTOMapper::toStudentDTO).toList()
        );
    }
}
