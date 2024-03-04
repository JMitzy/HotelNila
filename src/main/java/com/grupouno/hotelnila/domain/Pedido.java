/*
 * @file Pedido.java;
 * @Autor (c)2024 AndersonDietrich
 * @Created 3 mar. 2024,23:24:36
 */
package com.grupouno.hotelnila.domain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * The Class Pedido.
 */
@Entity
@Data
public class Pedido {
	
	/** The id pedido. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPedido;
	
	/** The estado. */
	private String estado;
	
	/** The descripcion. */
	private String descripcion;
	
	/** The precio. */
	private Double precio;
	
	/** The fecha pedido. */
	private Date fechaPedido;
	
	/** The fecha entrega. */
	private Date fechaEntrega;
	
	/** The cliente. */
	@ManyToOne //Relacion de muchos a uno
	@JsonBackReference
	private Cliente cliente;
	
	/** The id extra. */
	//RECURSIVO
	@ElementCollection
    private List<String> extras = new ArrayList<>();
	

	/** The factura. */
	@ManyToOne //Relacion de muchos a uno
	@JsonBackReference
	private Factura factura;


}