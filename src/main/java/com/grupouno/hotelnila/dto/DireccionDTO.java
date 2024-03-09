/*
 * @file DireccionDTO.java;
 * @Autor (c)2024 JhenniferMendoza
 * @Created 3 mar. 2024,11:27:52
 */
package com.grupouno.hotelnila.dto;



import javax.validation.constraints.Size;

import com.grupouno.hotelnila.domain.Cliente;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import com.grupouno.hotelnila.domain.Cliente;

import lombok.Data;


/**
 * DTO de dirección.
 */
@Data
public class DireccionDTO {
	
	/** Id de la dirección */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDireccion;
	
	/** Nombre de la dirección */
	@NotBlank
	@Size(max=50)
	private String nombreDirec;
	
	/** Ciudad en la que se encuentra la dirección */
	@Size(max=30)
	@NotBlank
	private String ciudad;
	
	/** Cliente a quien le pertenece la dirección */
	private Cliente cliente; 
}
