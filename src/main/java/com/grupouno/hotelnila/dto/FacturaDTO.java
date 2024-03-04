/*
 * @file FacturaDTO.java;
 * @Autor (c)2024 AndersonDietrich
 * @Created 3 mar. 2024,23:15:07
 */
package com.grupouno.hotelnila.dto;

import java.sql.Date;

import com.grupouno.hotelnila.domain.Reserva;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * The Class FacturaDTO.
 */
@Data
public class FacturaDTO {

	/** The id factura. */
	private Long idFactura;
	
	/** The monto total. */
	private Double montoTotal;
	
	/** The igv. */
	private Double igv;
	
	/** The descripcion. */
	private String descripcion;
	
	/** The fecha. */
	private Date fecha;
	
	private Reserva reserva;
}
