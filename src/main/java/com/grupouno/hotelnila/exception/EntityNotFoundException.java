/*
 * @file EntityNotFoundException.java;
 * @Autor (c)2024 JhenniferMendoza
 * @Created 8 mar. 2024,17:47:14
 */
package com.grupouno.hotelnila.exception;


/**
 * Excepción que indica que una entidad no ha sido encontrada.
 */
public class EntityNotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;

    /**
     * Constructor que acepta un mensaje de error.
     *
     * @param message El mensaje de error asociado con la excepción.
     */
    public EntityNotFoundException(String message) {
        super(message);
    }
}
