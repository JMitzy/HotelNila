/*
 * @file RecepcionistaDTO.java;
 * @Autor (c)2024 ErickBecerra
 * @Created 3 mar. 2024,16:14:36
 */


package com.grupouno.hotelnila.dto;

import java.util.ArrayList;
import java.util.List;

import com.grupouno.hotelnila.domain.Reserva;

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
	private String nombre;
	
	/** Apellido paterno del recepcionista. */
	private String apePat;
	
	/** Apellido materno del recepcionista. */
	private String apeMat;
	
	/** Número de telefono del recpcionista. */
	private String telefono;
	
	/** Turno de trabajo del recepcionista. */
	private String turno;

	/** Lista de reservas echas por el recepcionista. */
	private List<Reserva> reservas = new ArrayList<>();	
	
}
