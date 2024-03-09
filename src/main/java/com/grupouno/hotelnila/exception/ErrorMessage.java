/*
 * @file ErrorMessage.java;
 * @Autor (c)2024 JhenniferMendoza
 * @Created 8 mar. 2024,17:55:57
 */
package com.grupouno.hotelnila.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

/**
 * Clase para representar mensajes de error personalizado.
 */
public class ErrorMessage {
	
	/** * Código de estado HTTP asociado con el mensaje de error.*/
	private int statusCode;
    
    /** Fecha y hora en que se generó el mensaje de error. */
    private LocalDateTime timestamp;
    
    /** Mensaje de error. */
    private String mensaje;
    
    /** Descripción detallada del error. */
    private String descripcion;

    /**
    * Constructor para inicializar un objeto ErrorMessage.
     *
     * @param statusCode  Código de estado HTTP asociado con el mensaje de error.
     * @param mensaje     Mensaje de error.
     * @param descripcion Descripción detallada del error.
     */
    public ErrorMessage(HttpStatus statusCode, String mensaje, String descripcion) {
        this.statusCode = statusCode.value();
        this.timestamp = LocalDateTime.now();
        this.mensaje = mensaje;
        this.descripcion = descripcion;
    }
	
	/** Mensaje de error para cuando un cliente no es encontrado.*/
	public static final String CLIENTE_NOT_FOUND = "El cliente con el id proporcionado no fue encontrado";
	
	/** Mensaje de error para cuando una direccion no es encontrada.*/
	public static final String DIRECCION_NOT_FOUND = "La dirección con el id proporcionado no fue encontrada";
	
	/** Mensaje de error para cuando una habitacion no es encontrada.*/
	public static final String HABITACION_NOT_FOUND="La habitación con el id proporcionado no fue encontrada";
	
	/** Mensaje de error para cuando una reserva no es encontrada.*/
	public static final String RESERVA_NOT_FOUND="La reserva con el id proporcionado no fue encontrada";
	
	/** Mensaje de error para cuando un recepcionista no es encontrado. */
	public static final String RECEPCIONISTA_NOT_FOUND = "El Recepcionista con el id proporcionado no fue encontrado";
	
	/** Mensaje de error para cuando un comprobante no es encontrado. */
	public static final String COMPROBANTE_NOT_FOUND = "El comprobante con el id proporcionado no fue encontrada";
	
	/** Mensaje de error para cuando un pedido no es encontrado. */
	public static final String PEDIDO_NOT_FOUND = "El pedido con el id proporcionado no fue encontrado";

	/** Mensaje de error para cuando un detalle de habitacion no es encontrado. */
	public static final String DETALLEHABITACION_NOT_FOUND = "El detalle de reserva con el id proporcionado no fue encontrado";

	/** Mensaje de error para fecha invalida . */
	public static final String FECHA_INVALID = "La fecha de inicio debe ser anterior a la fecha fin";

}
