package vn.techmaster.usermanagement.controller;

import org.hibernate.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import vn.techmaster.usermanagement.exception.BadRequestException;
import vn.techmaster.usermanagement.exception.ErrorExceptionMessage;
import vn.techmaster.usermanagement.exception.NotFoundException;
import vn.techmaster.usermanagement.model.ErrorResponse;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorHandleController {
    // TODO: write code for handling other exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST, errors);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

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
        return new ErrorExceptionMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ErrorExceptionMessage handleNotFoundException(NotFoundException ex){
        return new ErrorExceptionMessage(HttpStatus.NOT_FOUND, ex.getMessage());
    }
}
