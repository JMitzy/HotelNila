/*
 * @file DetalleReservaDTO.java;
 * @Autor (c)2024 JuanRuiz
 * @Created 5 mar. 2024,03:08:58
 */
package com.grupouno.hotelnila.dto;

import com.grupouno.hotelnila.domain.Habitacion;
import com.grupouno.hotelnila.domain.Reserva;

import lombok.Data;

/**
 * The Class DetalleReservaDTO.
 */
@Data
public class DetalleReservaDTO {
    
    /** The id detalle reserva. */
    private Long idDetalleReserva;
    
    /** The pago reserva. */
    private float pagoReserva;
    
    /** The estado reserva. */
    private boolean estadoReserva;
    
    /** The habitacion. */
    private Habitacion habitacion;
    
    /** The reserva. */
    private Reserva reserva;
}