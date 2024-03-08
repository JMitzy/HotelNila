/*
 * @file RecepcionistaDTO.java;
 * @Autor (c)2024 ErickBecerra
 * @Created 3 mar. 2024,16:14:36
 */


package com.grupouno.hotelnila.dto;

import java.util.ArrayList;
import java.util.List;

import com.grupouno.hotelnila.domain.Reserva;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * The Class RecepcionistaDTO.
 */
@Data
public class RecepcionistaDTO {

	/** El id del recepcionista. */
	
	private Long idRecepcionista;
	
	/** Nombre del recepcionista. */
	@Size(max = 30,message="excede el número de carácteres permitidos")
	@NotBlank
	private String nombre;
	
	/** Apellido paterno del recepcionista. */
	@Size(max = 15,message="excede el número de carácteres permitidos")
	@NotBlank
	private String apePat;
	
	/** Apellido materno del recepcionista. */
	@Size(max = 15,message="excede el número de carácteres permitidos")
	@NotBlank
	private String apeMat;
	
	/** Número de telefono del recpcionista. */
	@Digits(integer = 9, fraction = 0, message = "valor numérico fuera de límites (se esperaba un número de teléfono 123456789)")
	@NotBlank(message = "El campo teléfono no puede estar vacío")
	private String telefono;
	
	/** Turno de trabajo del recepcionista. */
	@NotBlank
	@Pattern(regexp = "^(Mañana|Tarde)$", message = "Solo existe turno Mañana y Tarde")
	private String turno;

	/** Lista de reservas echas por el recepcionista. */
	private List<Reserva> reservas = new ArrayList<>();	
	
}
