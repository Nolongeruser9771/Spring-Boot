package com.example.classmanagement.Service;

import com.example.classmanagement.dto.AddStudentRequest;
import com.example.classmanagement.dto.ClazzDTO;

import java.util.List;

public interface ClazzService {
    List<ClazzDTO> findAllClazz();
    void addStudentToClazz(Integer clazzId, AddStudentRequest addStudentRequest);
}
