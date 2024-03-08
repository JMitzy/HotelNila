/*
 * @file HabitacionDTO.java;
 * @Autor (c)2024 JuanRuiz
 * @Created 5 mar. 2024,03:09:05
 */
package com.grupouno.hotelnila.dto;

import java.util.ArrayList;
import java.util.List;

import com.grupouno.hotelnila.domain.Reserva;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
	@NotNull(message = "El número de habitación no puede ser nula")
    private String nroHabitacion;
    
    /** The tipo habitacion. */
	@NotBlank(message = "Tipo de habitación no pueden estar en blanco")
    private String tipoHabitacion;
    
    /** The estado. */
	@NotBlank(message = "Estado de habitación no pueden estar en blanco")
	@Pattern(regexp = "^(Disponible|No disponible)$", message = "La habitación solo puede tener el estado Disponible o No disponible")
    private String estado;
    
    /** The precio. */
	@NotNull(message = "El precio de habitación no puede ser nula")
    private Float precio;
    
    /** The reserva. */
    private List<Reserva> reserva = new ArrayList<>();
}
