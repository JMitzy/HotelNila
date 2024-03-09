
/*
 * @file Comprobante.java;
 * @Autor (c)2024 AndersonDietrich
 * @Created 3 mar. 2024,23:24:58
 */

package com.grupouno.hotelnila.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

// TODO: Auto-generated Javadoc
/**
 * The Class Factura.
 */
@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idComprobante")
public class Comprobante extends RepresentationModel<Comprobante>{
	

	/** Id del comprobante */

	/** Id de la factura */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idComprobante;
	
	/** Fecha en la que se crea el comprobante */

	

	@NotNull(message = "La fecha del comprobante no puede ser nula")
    //@Past(message = "La fecha del comprobante debe estar en el pasado")
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date fechaComprobante;
    
	private String estado;
	
	/** Reserva a la que está asociada el comprobante */
	@OneToOne //Relacion de uno a uno
	//@JsonBackReference
	@JoinColumn(name = "res_id")
	private Reserva reserva;
	
	
	



}