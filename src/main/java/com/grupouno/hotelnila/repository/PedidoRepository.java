/*
 * @file PedidoRepository.java;
 * @Autor (c)2024 AndersonDietrich
 * @Created 3 mar. 2024,23:25:39
 */

package com.grupouno.hotelnila.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.grupouno.hotelnila.domain.Pedido;


/**
 * The Interface PedidoRepository.
 */
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
}
