/*
 * @file IllegalOperationException.java;
 * @Autor (c)2024 JhenniferMendoza
 * @Created 8 mar. 2024,18:09:35
 */
package com.grupouno.hotelnila.exception;


/**
 * Excepción que indica una operación ilegal.
 */
public class IllegalOperationException extends Exception{
	
	private static final long serialVersionUID = 1L;

    /**
     * Constructor que acepta un mensaje de error.
     *
     * @param message El mensaje de error asociado con la excepción.
     */
    public  IllegalOperationException(String message) {
        super(message);
    }

}
