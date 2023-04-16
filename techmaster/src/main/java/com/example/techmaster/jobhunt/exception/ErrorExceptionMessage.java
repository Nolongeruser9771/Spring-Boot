package com.example.techmaster.jobhunt.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorExceptionMessage {
    HttpStatus httpStatus;
    String message;
}
