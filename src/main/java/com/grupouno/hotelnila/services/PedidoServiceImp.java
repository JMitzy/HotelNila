/*
 * @file PedidoServiceImp.java;
 * @Autor (c)2024 AndersonDietrich
 * @Created 3 mar. 2024,22:03:08
 */
package com.grupouno.hotelnila.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupouno.hotelnila.domain.Pedido;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.ErrorMessage;
import com.grupouno.hotelnila.exception.IllegalOperationException;
import com.grupouno.hotelnila.repository.ClienteRepository;
import com.grupouno.hotelnila.repository.PedidoRepository;

import jakarta.transaction.Transactional;

// TODO: Auto-generated Javadoc
/**
 * The Class PedidoServiceImp.
 */
@Service
public class PedidoServiceImp implements PedidoService {
	
	@Autowired
	private PedidoRepository pedRep;
	
	@Autowired
	private ClienteRepository cliRep;
	
	
	/**
	 * Listar pedidos.
	 *
	 * @return the list
	 */
	@Override
	@Transactional
	public List<Pedido> listarPedidos() {
		// TODO Auto-generated method stub
		return (List<Pedido>)pedRep.findAll();
	}

	/**
	 * Buscar por id pedido.
	 *
	 * @param idCliente the id cliente
	 * @return the pedido
	 * @throws EntityNotFoundException the entity not found exception
	 */
	@Override
	@Transactional
	public Pedido buscarPorIdPedido(Long idPedido) throws EntityNotFoundException {
		Optional<Pedido> pedidos = pedRep.findById(idPedido);
		if(pedidos.isEmpty()){
			throw new EntityNotFoundException(ErrorMessage.PEDIDO_NOT_FOUND);
		}
		// TODO Auto-generated method stub
		return pedidos.get();
	}

	/**
	 * Crear pedido.
	 *
	 * @param pedido the pedido
	 * @return the pedido
	 * @throws IllegalOperationException the illegal operation exception
	 */
	@Override
	@Transactional
	public Pedido crearPedido(Pedido pedido) throws IllegalOperationException {
		// TODO Auto-generated method stub
		return pedRep.save(pedido);
	}

	/**
	 * Actualizar pedido.
	 *
	 * @param idPedido the id pedido
	 * @param pedido the pedido
	 * @return the pedido
	 * @throws EntityNotFoundException the entity not found exception
	 * @throws IllegalOperationException the illegal operation exception
	 */
	@Override
	public Pedido actualizarPedido(Long idPedido, Pedido pedido)
			throws EntityNotFoundException, IllegalOperationException {
		Optional<Pedido> pedidoEntity = pedRep.findById(idPedido);
		if(pedidoEntity.isEmpty()){
			throw new EntityNotFoundException(ErrorMessage.PEDIDO_NOT_FOUND);
		}
		pedido.setIdPedido(idPedido);
		// TODO Auto-generated method stub
		return pedRep.save(pedido);
	}

	/**
	 * Eliminar pedido.
	 *
	 * @param idPedido the id pedido
	 * @throws EntityNotFoundException the entity not found exception
	 * @throws IllegalOperationException the illegal operation exception
	 */
	@Override
	public void eliminarPedido(Long idPedido) throws EntityNotFoundException, IllegalOperationException {
		// TODO Auto-generated method stub

	}

	/**
	 * Asignar cliente.
	 *
	 * @param idPedido the id pedido
	 * @param idCLiente the id C liente
	 * @return the pedido
	 * @throws EntityNotFoundException the entity not found exception
	 * @throws IllegalOperationException the illegal operation exception
	 */
	@Override
	public Pedido asignarCliente(Long idPedido, Long idCLiente)
			throws EntityNotFoundException, IllegalOperationException {
		// TODO Auto-generated method stub
		return null;
	}

}
