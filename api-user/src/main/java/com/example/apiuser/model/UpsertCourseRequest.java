package com.example.apiuser.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpsertCourseRequest {
    @NotNull(message = "Name not null!")
    @NotBlank(message = "Name not blank!")
    private String name;

    @NotNull(message = "Name not null!")
    @NotBlank(message = "Description not blank!")
    @Size(min = 50)
    private String description;

    @NotNull(message = "Name not null!")
    @NotBlank(message = "Type not blank!")
    private String type;

    private List<String> topics;
    private String thumbnail;

    @NotNull(message = "UserId not null!")
    private Integer userId;
}