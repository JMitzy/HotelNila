package com.grupouno.hotelnila.domain;
import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Pedidos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPedido;
	private String estado;
	private String descripcion;
	private Double precio;
	private Date fechaPedido;
	private Date fechaEntrega;
}
