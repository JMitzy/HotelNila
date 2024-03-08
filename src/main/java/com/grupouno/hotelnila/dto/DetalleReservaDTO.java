/*
 * @file DetalleReservaDTO.java;
 * @Autor (c)2024 JuanRuiz
 * @Created 5 mar. 2024,03:08:58
 */
package com.grupouno.hotelnila.dto;

import com.grupouno.hotelnila.domain.Habitacion;
import com.grupouno.hotelnila.domain.Reserva;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * The Class DetalleReservaDTO.
 */
@Data
public class DetalleReservaDTO {
    
    /** The id detalle reserva. */
	
    private Long idDetalleReserva;
    
    /** The pago reserva. */
	@NotBlank
    private float pagoReserva;
    
    /** The estado reserva. */
    private boolean estadoReserva;
    
    /** The habitacion. */
    private Habitacion habitacion;
    
    /** The reserva. */
    private Reserva reserva;
}