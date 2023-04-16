package com.example.techmaster.jobhunt.exception;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ErrorExceptionMessage handleBadRequestException(BadRequestException ex, WebRequest req){
        return new ErrorExceptionMessage(HttpStatus.BAD_REQUEST,ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ErrorExceptionMessage handleException(Exception ex, WebRequest req){
        return new ErrorExceptionMessage(HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage());
    }

    @ExceptionHandler(TypeMismatchException.class)
    public ErrorExceptionMessage handleTypeMisMatch(TypeMismatchException ex) {
        return new ErrorExceptionMessage(HttpStatus.BAD_REQUEST, ex.getErrorCode());
    }
}
