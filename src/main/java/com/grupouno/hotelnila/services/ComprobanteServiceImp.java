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

import com.grupouno.hotelnila.domain.Comprobante;
import com.grupouno.hotelnila.domain.Reserva;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.ErrorMessage;
import com.grupouno.hotelnila.exception.IllegalOperationException;
import com.grupouno.hotelnila.repository.ComprobanteRepository;
import com.grupouno.hotelnila.repository.ReservaRepository;

import jakarta.transaction.Transactional;


/**
 * The Class FacturaServiceImp.
 */
@Service
public class ComprobanteServiceImp implements ComprobanteService {
	
	
	@Autowired
	private ComprobanteRepository facRep;
	
	@Autowired
	private ReservaRepository resRep;
	
	/**
	 * Listar facturas.
	 *
	 * @return lista de facturas
	 */
	
	@Override
	@Transactional
	public List<Comprobante> listarComprobantes() {
		// TODO Auto-generated method stub
		return (List<Comprobante>) facRep.findAll();
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
	public Comprobante buscarPorIdComprobante(Long idComprobante) throws EntityNotFoundException {
		Optional<Comprobante> facturas = facRep.findById(idComprobante);
		if (facturas.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.COMPROBANTE_NOT_FOUND);
		}
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
	public Comprobante crearComprobante(Comprobante comprobante) throws IllegalOperationException {
		return facRep.save(comprobante);
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
	public Comprobante actualizarComprobante(Long idComprobante, Comprobante comprobante)
			throws EntityNotFoundException, IllegalOperationException {
		Optional<Comprobante> comprobanteEntity = facRep.findById(idComprobante);
		if (comprobanteEntity.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.COMPROBANTE_NOT_FOUND);
		}
		comprobante.setIdComprobante(idComprobante);
		// TODO Auto-generated method stub
		return facRep.save(comprobante);
	}

	/**
	 * Eliminar factura.
	 *
	 * @param idFactura the id factura
	 * @throws EntityNotFoundException the entity not found exception
	 * @throws IllegalOperationException the illegal operation exception
	 */
	@Override
	@Transactional
	public void eliminarComprobante(Long idComprobante) throws EntityNotFoundException, IllegalOperationException {
		Comprobante comprobante = facRep.findById(idComprobante).orElseThrow(
                ()->new EntityNotFoundException(ErrorMessage.COMPROBANTE_NOT_FOUND)
        );
		facRep.deleteById(idComprobante);
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
	@Transactional
	public Comprobante asignarReserva(Long idComprobante, Long idReserva) 
			throws EntityNotFoundException, IllegalOperationException{
		
	Comprobante comprobante = facRep.findById(idComprobante).orElseThrow(
            ()->new EntityNotFoundException(ErrorMessage.COMPROBANTE_NOT_FOUND)
    );
	
	Reserva reserva = resRep.findById(idReserva).orElseThrow(
            ()->new EntityNotFoundException(ErrorMessage.RESERVA_NOT_FOUND)
    );
	
	// Verificar si la reserva ya tiene un comprobate asignado
    if (reserva.getComprobante() != null) {
        throw new IllegalOperationException("La reserva ya tiene un comprobante asignado.");
	}
    comprobante.setReserva(reserva);
    facRep.save(comprobante);
	return comprobante;


}
	}
