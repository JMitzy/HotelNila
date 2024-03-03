/*
 * @file Cliente.java;
 * @Autor (c)2024 JhenniferMendoza
 * @Created 2 mar. 2024,17:24:44
 */
package com.grupouno.hotelnila.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;


/**
 * Clase que representa un cliente en la persistencia.
 */
@Entity
@Data
public class Cliente {
	
	/** Representa el identificador único para la entidad Cliente. */
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idCliente;
	
	/** Representa el nombre del cliente. Limitado a un máximo de 30 caracteres */
	@Size(max=30)
	private String nombre;
	
	/** Representa el apellido paterno del cliente. Limitado a un máximo de 15 caracteres */
    @Size(max=15)
	private String apePat;
	
	/** Representa el apellido materno del cliente. Limitado a un máximo de 15 caracteres */
	@Size(max=15)
	private String apeMat;
	
	/** Representa el DNI del cliente. Limitado a 8 dígitos */
	
	@Digits(integer = 8, fraction = 0)
	private String dni;
	
	/** Representa el número de teléfono del cliente.Limitado 9 dígitos.*/
	
	@Digits(integer =9, fraction = 0)
	private String telefono;
	
	/**  Representa la dirección de correo electrónico del cliente */
	@Email
	private String email;
	
	/** Representa la dirección del cliente.  */
	@OneToOne(mappedBy = "cliente")
	@JsonManagedReference
	private Direccion direc;
	
	/** Representa la lista de pedidos realizados por el cliente.  */
	@OneToMany (mappedBy = "cliente")
	@JsonManagedReference
	private List<Pedido> pedidos = new ArrayList<>();
	
	
	/** Representa la lista de reservas realizadas por el cliente */
	@OneToMany (mappedBy = "cliente")
	@JsonManagedReference
	private List<Reserva> reservas = new ArrayList<>();
	
}