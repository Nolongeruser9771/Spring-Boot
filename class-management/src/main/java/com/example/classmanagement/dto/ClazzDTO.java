package com.example.classmanagement.dto;

import java.util.List;

public record ClazzDTO(int id,
                       String address,
                       List<StudentDTO> students) {
}
