/*
 * @file ClienteDTO.java;
 * @Autor (c)2024 JhenniferMendoza
 * @Created 3 mar. 2024,10:48:14
 */
package com.grupouno.hotelnila.dto;

import java.util.ArrayList;
import java.util.List;

import com.grupouno.hotelnila.domain.Direccion;
import com.grupouno.hotelnila.domain.Reserva;


import lombok.Data;


// TODO: Auto-generated Javadoc
/**
 * DTO del cliente.
 */
@Data
public class ClienteDTO {
	
	/** Id del cliente. */
	private Long idCliente;
	
	/** Nombre del cliente. */
	private String nombreCli;
	
	/** Apellido paterno del cliente */
	private String apePat;
	
	/** Apellido materno del cliente */
	private String apeMat;
	
	/** Dni del cliente*/
	private String dni;
	
	/** Telefono del cliente*/
	private String telefono;
	
	/** Email del cliente */
	private String email;
	
	/** Direccion del cliente*/
	private Direccion direc;
	
	/** Reservas realizadas por el cliente*/
	private List<Reserva> reservas = new ArrayList<>();
}
