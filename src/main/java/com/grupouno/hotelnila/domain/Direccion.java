/*
 * @file Direccion.java;
 * @Autor (c)2024 JhenniferMendoza
 * @Created 2 mar. 2024,17:18:48
 */
package com.grupouno.hotelnila.domain;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

/**
 *  Clase que representa una dirección en la persistencia.
 */
@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idDireccion")
public class Direccion {
	
	/**  Representa el identificador único de la dirección */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDireccion;
	
	/** Representa el nombre o descripción de la dirección. Limitado a un máximo de 50 caracteres.*/
	@Size(max=50)
	private String nombreDirec;
	
	/** Representa la ciudad en la que se encuentra la dirección. */
	@Size(max=30)
	private String ciudad;
	
	/**Representa el cliente asociado a esta dirección. */
	 @OneToOne
	 @JoinColumn(name = "cliente_id")
	 @JsonBackReference
	private Cliente cliente; 
	

}