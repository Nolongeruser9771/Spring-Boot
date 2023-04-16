package com.example.techmaster.jobhunt.model;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employer {
    static int AUTO_ID = 0;
    private int id;
    private String name;
    private int size;
    private List<Job> jobs;
    private List<Applicant> applicants;
    private String phone;

    public Employer(String name, int size, String phone) {
        AUTO_ID++;
        this.id = AUTO_ID;
        this.name = name;
        this.size = size;
        this.phone = phone;
        this.jobs = new ArrayList<>();
        this.applicants = new ArrayList<>();
    }
}
