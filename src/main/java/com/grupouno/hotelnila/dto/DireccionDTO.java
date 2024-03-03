/*
 * @file DireccionDTO.java;
 * @Autor (c)2024 JhenniferMendoza
 * @Created 3 mar. 2024,11:27:52
 */
package com.grupouno.hotelnila.dto;


import com.grupouno.hotelnila.domain.Cliente;

import lombok.Data;


// TODO: Auto-generated Javadoc
/**
 * The Class DireccionDTO.
 */
@Data
public class DireccionDTO {
	
	/** The id direccion. */
	private Long idDireccion;
	
	/** The nombre direc. */
	private String nombreDirec;
	
	/** The ciudad. */
	private String ciudad;
	
	/** The cliente. */
	private Cliente cliente; 
}
