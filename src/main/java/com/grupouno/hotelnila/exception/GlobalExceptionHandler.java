/*
 * @file GlobalExceptionHandler.java;
 * @Autor (c)2024 JhenniferMendoza
 * @Created 8 mar. 2024,17:50:55
 */
package com.grupouno.hotelnila.exception;

import java.util.HashMap;
import java.util.Map;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.grupouno.hotelnila.util.ApiResponse;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

// TODO: Auto-generated Javadoc
/**
 * Clase que maneja las excepciones globales en la aplicación.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    
    /**
      * Maneja las excepciones de IllegalOperationException.
     *
     * @param ex La excepción de IllegalOperationException.
     * @return ResponseEntity con un mensaje de error y un código de estado HTTP 400.
     */
    @ExceptionHandler(IllegalOperationException.class)
    public ResponseEntity<Object> handleIllegalOperationException(IllegalOperationException ex) {
        ApiResponse<Object> response = new ApiResponse<>(false, ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    
    /**
     * Maneja las excepciones generales de Exception.
     *
     * @param ex    La excepción de Exception.
     * @param request La solicitud web donde ocurrió la excepción.
     * @return ResponseEntity con un mensaje de error y un código de estado HTTP 500.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> globalExceptionHandler(Exception ex, WebRequest request){
    	ApiResponse<Object> response = new ApiResponse<>(false, ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
    
    /**
     * Maneja las excepciones de EntityNotFoundException.
     *
     * @param ex      La excepción de EntityNotFoundException.
     * @param request La solicitud web donde ocurrió la excepción.
     * @return ResponseEntity con un mensaje de error y un código de estado HTTP 404.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> resourceNotFoundException(EntityNotFoundException ex, WebRequest request){
    	ApiResponse<Object> response = new ApiResponse<>(false, ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
   

    /**
     * Maneja las excepciones de ConstraintViolationException.
     *
     * @param ex La excepción de ConstraintViolationException.
     * @return ResponseEntity con un mensaje de error de validación y un código de estado HTTP 400.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errores = new HashMap<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errores.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        ApiResponse<Object> response = new ApiResponse<>(false, "Error de validación", errores);
        return ResponseEntity.badRequest().body(response);
    }
    
    /*@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> manejarExcepcionDeValidacion(MethodArgumentNotValidException ex) {
        String mensaje = ex.getBindingResult().getFieldError().getDefaultMessage();
        return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
    }*/
       
    /*@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> manejarExcepcionDeValidacion(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        String mensaje = result.getFieldError().getDefaultMessage();
        Map<String, String> errores = result.getFieldErrors().stream()
            .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        ApiResponse<Map<String, String>> apiResponse = new ApiResponse<>(false, mensaje, errores);
        return ResponseEntity.badRequest().body(apiResponse);
    }*/
}