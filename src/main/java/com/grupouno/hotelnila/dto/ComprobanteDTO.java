/*
 * @file ComprobanteDTO.java;
 * @Autor (c)2024 AndersonDietrich
 * @Created 3 mar. 2024,23:15:07
 */
package com.grupouno.hotelnila.dto;

import java.sql.Date;


import org.springframework.format.annotation.DateTimeFormat;

import com.grupouno.hotelnila.domain.Reserva;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

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
	@NotNull(message = "La fecha del comprobante no puede ser nula")
    //@Past(message = "La fecha del comprobante debe estar en el pasado")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaComprobante;
	
	@Pattern(regexp = "^(Pagado|No pagado)$", message = "El comprobante solo puede tener el estado de Pagado o No pagado")
	private String estado;
	
	/** The reserva. */
	private Reserva reserva;
}
