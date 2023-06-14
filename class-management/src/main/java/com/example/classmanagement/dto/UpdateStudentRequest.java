package com.example.classmanagement.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStudentRequest {
    @Nullable
    private String name;

    @Nullable
    @Min(value = 0, message = "Age should not less than 0")
    private int age;

    @Nullable
    @Pattern(regexp = "A|B|C|D|E|F", message = "Not valid ranking")
    private String ranking;

    @Nullable
    private int clazzId;
}
