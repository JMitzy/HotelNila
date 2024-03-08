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

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
	@Size(max=30)
	@NotBlank
	private String nombreCli;
	
	/** Apellido paterno del cliente */
	@Size(max=15)
	@NotBlank
	private String apePat;
	
	/** Apellido materno del cliente */
	@Size(max=15)
	@NotBlank
	private String apeMat;
	
	/** Dni del cliente*/
	@Column(unique=true)
	@Digits(integer = 8, fraction = 0)
	@NotBlank
	private String dni;
	
	/** Telefono del cliente*/
	@Digits(integer =9, fraction = 0)
	@NotBlank
	private String telefono;
	
	/** Email del cliente */
	@Email(message = "El formato del email es incorrec")
	@NotBlank
	private String email;
	
	/** Direccion del cliente*/
	private Direccion direc;
	
	/** Reservas realizadas por el cliente*/
	private List<Reserva> reservas = new ArrayList<>();
}
