package com.grupouno.hotelnila.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.grupouno.hotelnila.util.ApiResponse;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundException(EntityNotFoundException ex, WebRequest request){
        ErrorMessage mensaje = new ErrorMessage(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalOperationException.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundException(IllegalOperationException ex, WebRequest request){
        ErrorMessage mensaje = new ErrorMessage(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request){
        ErrorMessage mensaje = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errores = new HashMap<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errores.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        ApiResponse<Object> response = new ApiResponse<>(false, "Error de validación", errores);
        return ResponseEntity.badRequest().body(response);
    }
}