/*
 * @file FacturaRepository.java;
 * @Autor (c)2024 AndersonDietrich
 * @Created 3 mar. 2024,23:25:28
 */

package com.grupouno.hotelnila.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupouno.hotelnila.domain.Factura;
import com.grupouno.hotelnila.domain.Pedido;

// TODO: Auto-generated Javadoc
/**
 * The Interface FacturaRepository.
 */
public interface FacturaRepository extends JpaRepository<Factura, Long> {
	
	/**
	 *  * Busca factura por pedido.
	 *
	 * @param pedidos the pedidos
	 * @return the list
	 */
	List<Factura> findBypedidos(Pedido pedidos);
}
