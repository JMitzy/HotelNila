/*
 * @file DireccionDTO.java;
 * @Autor (c)2024 JhenniferMendoza
 * @Created 3 mar. 2024,11:27:52
 */
package com.grupouno.hotelnila.dto;


import com.grupouno.hotelnila.domain.Cliente;

import lombok.Data;


/**
 * DTO de dirección.
 */
@Data
public class DireccionDTO {
	
	/** Id de la dirección */
	private Long idDireccion;
	
	/** Nombre de la dirección */
	private String nombreDirec;
	
	/** Ciudad en la que se encuentra la dirección */
	private String ciudad;
	
	/** Cliente a quien le pertenece la dirección */
	private Cliente cliente; 
}
