/*
 * @file ReservaDTO.java;
 * @Autor (c)2024 JuanRuiz
 * @Created 5 mar. 2024,03:09:13
 */
package com.grupouno.hotelnila.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.grupouno.hotelnila.domain.Cliente;
import com.grupouno.hotelnila.domain.Comprobante;
import com.grupouno.hotelnila.domain.Habitacion;
import com.grupouno.hotelnila.domain.Recepcionista;

import lombok.Data;

/**
 * The Class ReservaDTO.
 */
@Data
public class ReservaDTO {
	
	/** The id reserva. */
	private Long idReserva;
	
	/** The fecha inicio. */
	private Date fechaInicio;
	
	/** The fecha fin. */
	private Date fechaFin;
	
	/** The habitaciones. */
	private List<Habitacion> habitaciones = new ArrayList<>();
	
	/** The recepcionista. */
	private Recepcionista recepcionista;
	
	/** The cliente. */
	private Cliente cliente;
	
	/** The factura. */
	private Comprobante factura;
}
