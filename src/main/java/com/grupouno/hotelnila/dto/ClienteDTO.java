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


/**
 * The Class ClienteDTO.
 */
@Data
public class ClienteDTO {
	
	private Long idCliente;
	private String nombreCli;
	private String apePat;
	private String apeMat;
	private String dni;
	private String telefono;
	private String email;
	private Direccion direc;
	private List<Reserva> reservas = new ArrayList<>();
}
