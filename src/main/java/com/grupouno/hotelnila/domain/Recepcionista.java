/*
 * @file Recepcionista.java;
 * @Autor (c)2024 ErickBecerra
 * @Created 2 mar. 2024,22:35:44
 */

package com.grupouno.hotelnila.domain;

import java.util.ArrayList;
import java.util.List;



import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * La clase Recepcionista es una entidad en la aplicación que 
 * representa a un recepcionista en el dominio del problema.
 * 
 */
@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idRecepcionista")
public class Recepcionista {

	/** El id del recepcionista. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRecepcionista;
	
	/** Nombre del recepcionista. */
	@Size(max = 30)
	@NotBlank
	private String nombre;
	
	/** Apellido paterno del recepcionista. */
	@Size(max = 15)
	@NotBlank
	private String apePat;
	
	/** Apellido materno del recepcionista. */
	@Size(max = 15)
	@NotBlank
	private String apeMat;
	
	/** Número de telefono del recpcionista. */
	@Digits(integer = 9, fraction = 0, message = "valor numérico fuera de límites (se esperaba un número de teléfono 123456789)")
	@NotBlank(message = "El campo no puede estar vacío")
	private String telefono;
	
	/** Turno de trabajo del recepcionista. */
	@NotBlank
	@Pattern(regexp = "^(Mañana|Tarde)$", message = "Solo existe turno Mañana y Tarde")
	private String turno;

	/** Lista de reservas echas por el recepcionista. */
	@OneToMany(mappedBy = "recepcionista")//, cascade = CascadeType.ALL, orphanRemoval = true)
	//@JsonManagedReference
	private List<Reserva> reservas = new ArrayList<>();

}

