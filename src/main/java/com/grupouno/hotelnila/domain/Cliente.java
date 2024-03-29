/*
 * @file Cliente.java;
 * @Autor (c)2024 JhenniferMendoza
 * @Created 2 mar. 2024,17:24:44
 */
package com.grupouno.hotelnila.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


/**
 * Clase que representa un cliente en la persistencia.
 */
@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idCliente")

public class Cliente extends RepresentationModel<Cliente>{
	
	/** Representa el identificador único para la entidad Cliente. */
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idCliente;
	
	/** Representa el nombre del cliente. Limitado a un máximo de 30 caracteres */

	private String nombreCli;
	
	/** Representa el apellido paterno del cliente. Limitado a un máximo de 15 caracteres */
    
	private String apePat;
	
	
	/** Representa el apellido materno del cliente. Limitado a un máximo de 15 caracteres */

	private String apeMat;
	
	/** Representa el DNI del cliente. Limitado a 8 dígitos */

	private String dni;
	
	/** Representa el número de teléfono del cliente.Limitado 9 dígitos.*/
	

	private String telefono;
	
	/**  Representa la dirección de correo electrónico del cliente */
	

	private String email;
	
	/** Representa la dirección del cliente.  */
	@OneToOne(mappedBy = "cliente")
	@JoinColumn(name = "direc_id")
	//@JsonManagedReference
	private Direccion direc;
	
	/** Representa la lista de reservas realizadas por el cliente */
	@OneToMany (mappedBy = "cliente")
	//@JsonManagedReference
	private List<Reserva> reservas = new ArrayList<>();
}	