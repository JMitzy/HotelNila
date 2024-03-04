/*
 * @file PedidoService.java;
 * @Autor (c)2024 AndersonDietrich
 * @Created 3 mar. 2024,21:46:45
 */
package com.grupouno.hotelnila.services;

import java.util.List;

import com.grupouno.hotelnila.domain.Pedido;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.IllegalOperationException;

/**
 * The Interface PedidoService.
 */
public interface PedidoService {

	List<Pedido>listarPedidos();
	
	Pedido buscarPorIdPedido(Long idCliente)throws EntityNotFoundException;
	
	Pedido crearPedido(Pedido pedido)throws IllegalOperationException;
	
	Pedido actualizarPedido(Long idPedido,Pedido pedido)throws EntityNotFoundException, IllegalOperationException;
	
	void eliminarPedido(Long idPedido)throws EntityNotFoundException, IllegalOperationException;
	
	Pedido asignarCliente(Long idPedido, Long idCLiente)throws EntityNotFoundException, IllegalOperationException;
	
}
