package com.grupouno.hotelnila.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ErrorMessage {
	private int statusCode;
    private LocalDateTime timestamp;
    private String mensaje;
    private String descripcion;

    public ErrorMessage(HttpStatus statusCode, String mensaje, String descripcion) {
        this.statusCode = statusCode.value();
        this.timestamp = LocalDateTime.now();
        this.mensaje = mensaje;
        this.descripcion = descripcion;
    }
	
	public static final String CLIENTE_NOT_FOUND = "El cliente con el id proporcionado no fue encontrado";
	public static final String DIRECCION_NOT_FOUND = "La dirección con el id proporcionado no fue encontrada";
	public static final String HABITACION_NOT_FOUND="La habitación con el id proporcionado no fue encontrada";
	public static final String RESERVA_NOT_FOUND="La reserva con el id proporcionado no fue encontrada";
	
	public static final String RECEPCIONISTA_NOT_FOUND = "El Recepcionista con el id proporcionado no fue encontrado";
	public static final String COMPROBANTE_NOT_FOUND = "El comprobante con el id proporcionado no fue encontrada";
	public static final String PEDIDO_NOT_FOUND = "El pedido con el id proporcionado no fue encontrado";
	public static final String DETALLEHABITACION_NOT_FOUND = "El Recepcionista con el id proporcionado no fue encontrado";
	
	public static final String FECHA_INVALID = "La fecha de inicio debe ser anterior a la fecha fin";

}
