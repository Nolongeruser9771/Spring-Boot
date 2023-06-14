package com.example.classmanagement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddStudentRequest {

    @NotNull(message = "Name is required")
    @NotEmpty(message = "Name is required")
    private String name;

    @NotNull(message = "Age is required")
    @Min(value = 0, message = "Age should not less than 0")
    private int age;

    @NotNull(message = "Ranking is required")
    @NotEmpty(message = "Ranking is required")
    @Pattern(regexp = "A|B|C|D|E|F", message = "Not valid ranking")
    private String ranking;
}
