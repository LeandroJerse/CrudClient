package com.example.crudClient.controller.handlers;

import com.example.crudClient.dto.errorDTO.CustomError;
import com.example.crudClient.dto.errorDTO.ValidationError;
import com.example.crudClient.service.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> handleResourceNotFoundException(ResourceNotFoundException e, HttpServletRequest request){

        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI() );
        return ResponseEntity.status(status).body(err);

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> methodArgumentNotValidation(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError err = new ValidationError(Instant.now(), status.value(), "Dados inváliodos", request.getRequestURI() );

        e.getBindingResult().getFieldErrors().forEach(f -> {
            err.addError(f.getField(), f.getDefaultMessage());
        });

        return ResponseEntity.status(status).body(err);
    }
}




