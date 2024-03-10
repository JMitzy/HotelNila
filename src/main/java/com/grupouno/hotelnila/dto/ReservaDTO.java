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

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

/**
 * The Class ReservaDTO.
 */
@Data
@ValidarFecha
public class ReservaDTO {
	
	/** The id reserva. */
	
	private Long idReserva;
	
	/** The fecha inicio. */
	@Temporal(TemporalType.DATE)
	@NotNull(message = "La fecha de inicio no puede ser nula")
	private Date fechaInicio;
	
	/** The fecha fin. */
	@Temporal(TemporalType.DATE)
    @NotNull(message = "La fecha de fin no puede ser nula")
	private Date fechaFin;
	
	/** The habitaciones. */
	private List<Habitacion> habitaciones = new ArrayList<>();
	
	/** The recepcionista. */
	private Recepcionista recepcionista;
	
	/** The cliente. */
	private Cliente cliente;
	
	/** The factura. */

	private Comprobante comprobante;

}
