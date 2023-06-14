package com.example.userapp.controller;

import com.example.userapp.exception.BadRequestException;
import com.example.userapp.exception.ErrorMessage;
import com.example.userapp.exception.FileHandleException;
import com.example.userapp.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandleController {

    @ExceptionHandler(NotFoundException.class)
    public ErrorMessage notFoundExceptionHandler(NotFoundException ex){
        return new ErrorMessage(HttpStatus.NOT_FOUND,ex.getMessage());
    }

    @ExceptionHandler(FileHandleException.class)
    public ErrorMessage FileHandleExceptionHandler(FileHandleException ex){
        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ErrorMessage BadRequestExceptionHandler(BadRequestException ex){
        return new ErrorMessage(HttpStatus.BAD_REQUEST,ex.getMessage());
    }
}
