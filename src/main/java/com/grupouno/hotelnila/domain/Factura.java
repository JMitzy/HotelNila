
/*
 * @file Factura.java;
 * @Autor (c)2024 AndersonDietrich
 * @Created 3 mar. 2024,23:24:58
 */

package com.grupouno.hotelnila.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.OneToOne;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

// TODO: Auto-generated Javadoc
/**
 * The Class Factura.
 */
@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idFactura")
public class Factura {
	
	/** The id factura. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFactura;
	
	/** The monto total. */
	private Double montoTotal;
	
	/** The igv. */
	private Double igv;
	
	/** The descripcion. */
	private String descripcion;
	
	/** The fecha. */
	private Date fecha;
	
	/** The reserva. */
	@OneToOne //Relacion de uno a uno
	//@JsonBackReference
	@JoinColumn(name = "res_id")
	private Reserva reserva;
	
	
	



}