package com.example.classmanagement.controller;

import com.example.classmanagement.exception.BadRequestException;
import com.example.classmanagement.exception.ErrorResponse;
import com.example.classmanagement.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionHandleController {

    @ExceptionHandler(BadRequestException.class)
    public ErrorResponse BadRequestExceptionHandler(BadRequestException ex){
        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage()).build();
    }

    @ExceptionHandler({NotFoundException.class, NoSuchElementException.class})
    public ErrorResponse NotFoundExceptionHandler(Exception ex){
        return ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(ex.getMessage()).build();
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse ExceptionHandler(Exception ex){
        return ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(ex.getMessage())
                .build();
    }

}
