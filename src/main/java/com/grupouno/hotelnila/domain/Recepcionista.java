/*
 * @file Recepcionista.java;
 * @Autor (c)2024 ErickBecerra
 * @Created 2 mar. 2024,22:35:44
 */

package com.grupouno.hotelnila.domain;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

// TODO: Auto-generated Javadoc
/**
 * La clase Recepcionista es una entidad en la aplicación que 
 * representa a un recepcionista en el dominio del problema.
 * 
 */
public class Recepcionista {

	/** El id del recepcionista. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRecepcionista;
	
	/** Nombre del recepcionista. */
	@Size(max = 30)
	private String nombre;
	
	/** Apellido paterno del recepcionista. */
	@Size(max = 15)
	private String apePat;
	
	/** Apellido materno del recepcionista. */
	@Size(max = 15)
	private String apeMat;
	
	/** Número de telefono del recpcionista. */
	@Pattern(regexp = "\\d{9}")
	private String telefono;
	
	/** Turno de trabajo del recepcionista. */
	@Pattern(regexp = "^(Mañana|Tarde)$")
	private String turno;

	/** Lista de reservas echas por el recepcionista. */
	@OneToMany(mappedBy = "recepcionista")//, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Reserva> reservas = new ArrayList<>();	

}

