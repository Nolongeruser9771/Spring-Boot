package com.example.ewallet.controller;

import com.example.ewallet.exception.ErrorResponse;
import com.example.ewallet.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandleController {

    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse handleNotFoundException(NotFoundException ex){
        return new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception ex){
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage());
    }
}
