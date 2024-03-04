
/*
 * @file Factura.java;
 * @Autor (c)2024 AndersonDietrich
 * @Created 3 mar. 2024,23:24:58
 */

package com.grupouno.hotelnila.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

// TODO: Auto-generated Javadoc
/**
 * The Class Factura.
 */
@Entity
@Data
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
	@ManyToOne //Relacion de muchos a uno
	@JsonBackReference
	private Reserva reserva;
	
	
	/** The pedidos. */
	@OneToMany(mappedBy = "factura")//, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Pedido> pedidos = new ArrayList<>();



}