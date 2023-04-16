package com.example.techmaster.jobhunt.repository;

import com.example.techmaster.jobhunt.model.Employer;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public interface EmployerRepoService {
    ConcurrentHashMap<Integer, Employer> initData();
    ConcurrentHashMap<Integer, Employer> getAllList();
    Employer addEmployer(Employer newEmployer);
    boolean isDuplicatedName(String name);
}
