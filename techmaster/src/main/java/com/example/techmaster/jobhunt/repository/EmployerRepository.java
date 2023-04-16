package com.example.techmaster.jobhunt.repository;

import com.example.techmaster.jobhunt.model.Employer;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class EmployerRepository implements EmployerRepoService{
    //data:
    @Override
    public ConcurrentHashMap<Integer, Employer> initData() {
        ConcurrentHashMap<Integer,Employer> employers = new ConcurrentHashMap<>();
        employers.put(1, new Employer("Samsung",50000,"0242004005"));
        employers.put(2, new Employer("Intel",50000,"0212022113"));
        employers.put(3, new Employer("Technology DXC",5000,"0232111222"));
        employers.put(4, new Employer("LG",50000,"0232224227"));
        employers.put(5, new Employer("Huawei",10000,"0222444000"));
        return employers;
    }

    ConcurrentHashMap<Integer,Employer> employers = initData();

    @Override
    public ConcurrentHashMap<Integer, Employer> getAllList() {
        return employers;
    }

    @Override
    public Employer addEmployer(Employer newEmployer) {
        employers.put(newEmployer.getId(),newEmployer);
        return newEmployer;
    }

    @Override
    public boolean isDuplicatedName(String name) {
        for (Employer empl: employers.values()) {
            if(empl.getName().compareToIgnoreCase(name) == 0) return true;
        }
        return false;
    }
}
