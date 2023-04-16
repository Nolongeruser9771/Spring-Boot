package com.example.techmaster.jobhunt.controller;

import com.example.techmaster.jobhunt.exception.BadRequestException;
import com.example.techmaster.jobhunt.model.Employer;
import com.example.techmaster.jobhunt.repository.EmployerRepoService;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
@Validated
public class HomeController {

    @Autowired
    EmployerRepoService employerRepository;

    @GetMapping("/home")
    public String home(){
        return "index";
    }

    @GetMapping("/employer-list")
    public String showEmployerList(Model model){
        model.addAttribute("employers",employerRepository.getAllList());
        return "employer-list";
    }

    @GetMapping("/employer-form")
    public String showEmployerForm(Model model){
        Employer employer = new Employer();
        model.addAttribute("employer",employer);
        return "employer-form";
    }

    @PostMapping("/employer-form")
    public String addEmployer(Model model,
                              @RequestParam @NotEmpty String name,
                              @RequestParam @NotNull int size,
                              @RequestParam @Pattern(regexp = "(02|[0-4])+([0-9]{6})") String phone){
        if(!employerRepository.isDuplicatedName(name.trim())) {
            Employer employer = new Employer(name.trim(), size, phone.trim());
            employerRepository.addEmployer(employer);
            model.addAttribute("employer", employer);
            model.addAttribute("notify", "Successfully added!");
            return "employer-form";
        }
        throw new BadRequestException("Company name already taken!");
    }
}
