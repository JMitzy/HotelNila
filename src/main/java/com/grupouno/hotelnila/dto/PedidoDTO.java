/*
 * @file PedidoDTO.java;
 * @Autor (c)2024 AndersonDietrich
 * @Created 3 mar. 2024,23:20:51
 */
package com.grupouno.hotelnila.dto;

import java.sql.Date;

import com.grupouno.hotelnila.domain.Cliente;
import com.grupouno.hotelnila.domain.Factura;
import com.grupouno.hotelnila.domain.Pedido;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * The Class PedidoDTO.
 */
@Data
public class PedidoDTO {

	/** The id pedido. */
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
	private Cliente cliente;
	
	/** The id extra. */
	private Pedido idExtra;
	
	/** The factura. */
	private Factura factura;
}
