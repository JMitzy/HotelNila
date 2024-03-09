/*
 * @file ApiResponse.java;
 * @Autor (c)2024 JhenniferMendoza
 * @Created 8 mar. 2024,17:43:51
 */
package com.grupouno.hotelnila.util;


import lombok.Data;


/**
 * Clase genérica para representar una respuesta de la API.
 *
 * @param <T> El tipo de datos que contiene la respuesta.
 */
@Data
public class ApiResponse<T> {
    
    /** * Indica si la operación fue exitosa.*/
    private boolean success; 
    
    /** Mensaje asociado con la respuesta. */
    private String message; 
    
    /** * Datos asociados con la respuesta. */
    private T data;
    
    /**
     * Constructor para inicializar una instancia de ApiResponse.
     *
     * @param success Indica si la operación fue exitosa.
     * @param message Mensaje asociado con la respuesta.
     * @param data    Datos asociados con la respuesta.
     */
    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

}
