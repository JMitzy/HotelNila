/*
 * @file ClienteDTO.java;
 * @Autor (c)2024 JhenniferMendoza
 * @Created 3 mar. 2024,10:48:14
 */
package com.grupouno.hotelnila.dto;

import java.util.ArrayList;
import java.util.List;

import com.grupouno.hotelnila.domain.Direccion;
import com.grupouno.hotelnila.domain.Pedido;
import com.grupouno.hotelnila.domain.Reserva;


import lombok.Data;


/**
 * The Class ClienteDTO.
 */
@Data
public class ClienteDTO {
	
	/** The id cliente. */
	private Long idCliente;
	
	/** The nombre cli. */
	private String nombreCli;
	
	/** The ape pat. */
	private String apePat;
	
	/** The ape mat. */
	private String apeMat;
	
	/** The dni. */
	private String dni;
	
	/** The telefono. */
	private String telefono;
	
	/** The email. */
	private String email;
	
	/** The direc. */
	private Direccion direc;
	
	/** The pedidos. */
	private List<Pedido> pedidos = new ArrayList<>();
	
	/** The reservas. */
	private List<Reserva> reservas = new ArrayList<>();
}
