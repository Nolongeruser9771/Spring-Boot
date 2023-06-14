package com.example.classmanagement.Service;

import com.example.classmanagement.dto.AddStudentRequest;
import com.example.classmanagement.dto.ClazzDTO;
import com.example.classmanagement.entity.Clazz;
import com.example.classmanagement.entity.Student;
import com.example.classmanagement.mapper.ClazzDTOMapper;
import com.example.classmanagement.repository.ClazzRepository;
import com.example.classmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService{
    @Autowired
    private ClazzRepository clazzRepository;

    @Autowired
    private StudentRepository studentRepository;

    //1. Hiển thị thông tin chi tiết khóa học
    public List<ClazzDTO> findAllClazz(){
        return clazzRepository.findAll().stream().map(ClazzDTOMapper::toClazzDTO).toList();
    }

    //2.Thêm học viên vào khóa học
    @Override
    public void addStudentToClazz(Integer clazzId, AddStudentRequest addStudentRequest) {
        Clazz targetClazz = clazzRepository.findById(clazzId)
                .orElseThrow(); //NoSuchElementException;
        Student newStudent = Student.builder()
                .name(addStudentRequest.getName())
                .age(addStudentRequest.getAge())
                .ranking(addStudentRequest.getRanking())
                .clazz(targetClazz).build();
        studentRepository.save(newStudent);
    }
}
