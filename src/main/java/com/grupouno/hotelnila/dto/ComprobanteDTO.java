/*
 * @file ComprobanteDTO.java;
 * @Autor (c)2024 AndersonDietrich
 * @Created 3 mar. 2024,23:15:07
 */
package com.grupouno.hotelnila.dto;

import java.sql.Date;

import com.grupouno.hotelnila.domain.Reserva;

import lombok.Data;

/**
 * The Class FacturaDTO.
 */
@Data
public class ComprobanteDTO {

	/** The id comprobante. */
	private Long idComprobante;
	
	/** The fecha comprobante. */
	private Date fechaComprobante;
	
	/** The reserva. */
	private Reserva reserva;
}
