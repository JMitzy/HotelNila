/*
 * @file HabitacionDTO.java;
 * @Autor (c)2024 JuanRuiz
 * @Created 5 mar. 2024,03:09:05
 */
package com.grupouno.hotelnila.dto;

import java.util.ArrayList;
import java.util.List;

import com.grupouno.hotelnila.domain.Reserva;

import lombok.Data;


// TODO: Auto-generated Javadoc
/**
 * The Class HabitacionDTO.
 */
@Data
public class HabitacionDTO {
	
	/** The id habitacion. */
	private Long idHabitacion;
    
    /** The nro habitacion. */
    private String nroHabitacion;
    
    /** The tipo habitacion. */
    private String tipoHabitacion;
    
    /** The estado. */
    private String estado;
    
    /** The precio. */
    private Float precio;
    
    /** The reserva. */
    private List<Reserva> reserva = new ArrayList<>();
}
