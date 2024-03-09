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
	@OneToMany(mappedBy = "recepcionista")//, cascade = CascadeType.ALL, orphanRemoval = true)
	//@JsonManagedReference
	private List<Reserva> reservas = new ArrayList<>();

}

