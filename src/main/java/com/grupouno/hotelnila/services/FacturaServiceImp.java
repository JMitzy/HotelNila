/*
 * @file FacturaServiceImp.java;
 * @Autor (c)2024 AndersonDietrich
 * @Created 3 mar. 2024,19:55:08
 */
package com.grupouno.hotelnila.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupouno.hotelnila.domain.Factura;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.ErrorMessage;
import com.grupouno.hotelnila.exception.IllegalOperationException;
import com.grupouno.hotelnila.repository.FacturaRepository;
import com.grupouno.hotelnila.repository.PedidoRepository;
import com.grupouno.hotelnila.repository.ReservaRepository;

import jakarta.transaction.Transactional;

// TODO: Auto-generated Javadoc
/**
 * The Class FacturaServiceImp.
 */
@Service
public class FacturaServiceImp implements FacturaService {
	
	
	@Autowired
	private FacturaRepository facRep;
	
	@Autowired
	private PedidoRepository pedRep;
	
	@Autowired
	private ReservaRepository resRep;
	
	/**
	 * Listar facturas.
	 *
	 * @return lista de facturas
	 */
	
	@Override
	@Transactional
	public List<Factura> listarFacturas() {
		// TODO Auto-generated method stub
		return (List<Factura>) facRep.findAll();
	}

	/**
	 * Buscar por id factura.
	 *
	 * @param idFactura the id factura
	 * @return the factura
	 * @throws EntityNotFoundException the entity not found exception
	 */
	@Override
	@Transactional
	public Factura buscarPorIdFactura(Long idFactura) throws EntityNotFoundException {
		Optional<Factura> facturas = facRep.findById(idFactura);
		if (facturas.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.FACTURA_NOT_FOUND);
		}
		// TODO Auto-generated method stub
		return facturas.get();
	}

	/**
	 * Crear factura.
	 *
	 * @param factura the factura
	 * @return the factura
	 * @throws EntityNotFoundException the entity not found exception
	 */
	@Override
	@Transactional
	public Factura crearFactura(Factura factura) throws IllegalOperationException {
		// TODO Auto-generated method stub
		return facRep.save(factura);
	}

	/**
	 * Actualizar factura.
	 *
	 * @param idFactura the id factura
	 * @param factura the factura
	 * @return the factura
	 * @throws EntityNotFoundException the entity not found exception
	 * @throws IllegalOperationException the illegal operation exception
	 */
	@Override
	@Transactional
	public Factura actualizarFactura(Long idFactura, Factura factura)
			throws EntityNotFoundException, IllegalOperationException {
		Optional<Factura> facturaEntity = facRep.findById(idFactura);
		if (facturaEntity.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.CLIENTE_NOT_FOUND);
		}
		factura.setIdFactura(idFactura);
		// TODO Auto-generated method stub
		return facRep.save(factura);
	}

	/**
	 * Eliminar factura.
	 *
	 * @param idFactura the id factura
	 * @throws EntityNotFoundException the entity not found exception
	 * @throws IllegalOperationException the illegal operation exception
	 */
	@Override
	public void eliminarFactura(Long idFactura) throws EntityNotFoundException, IllegalOperationException {
		// TODO Auto-generated method stub

	}

	/**
	 * Asignar reserva.
	 *
	 * @param idFactura the id factura
	 * @param idReserva the id reserva
	 * @return the factura
	 * @throws EntityNotFoundException the entity not found exception
	 * @throws IllegalOperationException the illegal operation exception
	 */
	@Override
	public Factura asignarReserva(Long idFactura, Long idReserva)
			throws EntityNotFoundException, IllegalOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Asignar pedido.
	 *
	 * @param idFactura the id factura
	 * @param idPedido the id pedido
	 * @return the factura
	 * @throws EntityNotFoundException the entity not found exception
	 * @throws IllegalOperationException the illegal operation exception
	 */
	@Override
	public Factura asignarPedido(Long idFactura, Long idPedido)
			throws EntityNotFoundException, IllegalOperationException {
		// TODO Auto-generated method stub
		return null;
	}

}
