/*
 * @file ReservaService.java;
 * @Autor (c)2024 JuanRuiz
 * @Created 5 mar. 2024,03:11:37
 */
package com.grupouno.hotelnila.services;

import java.util.List;
import com.grupouno.hotelnila.domain.Reserva;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.IllegalOperationException;

/**
 * The Interface ReservaService.
 */
public interface ReservaService {
	
	/**
	 * Listar reservas.
	 *
	 * @return the list
	 */
	List<Reserva>listarReservas();
	
	/**
	 * Buscar por id reserva.
	 *
	 * @param idReserva the id reserva
	 * @return the reserva
	 * @throws EntityNotFoundException the entity not found exception
	 */
	Reserva buscarPorIdReserva(Long idReserva) throws EntityNotFoundException;
	
	/**
	 * Crear reserva.
	 *
	 * @param reserva the reserva
	 * @return the reserva
	 * @throws IllegalOperationException the illegal operation exception
	 */
	Reserva crearReserva (Reserva reserva)throws IllegalOperationException;
	
	/**
	 * Actualizar reserva.
	 *
	 * @param idReserva the id reserva
	 * @param reserva the reserva
	 * @return the reserva
	 * @throws EntityNotFoundException the entity not found exception
	 * @throws IllegalOperationException the illegal operation exception
	 */
	Reserva actualizarReserva(Long idReserva, Reserva reserva) throws EntityNotFoundException, IllegalOperationException;
    
    /**
     * Asignar cliente.
     *
     * @param idReserva the id reserva
     * @param idCliente the id cliente
     * @return the reserva
     * @throws EntityNotFoundException the entity not found exception
     * @throws IllegalOperationException the illegal operation exception
     */
    Reserva asignarCliente(Long idReserva, Long idCliente) throws EntityNotFoundException, IllegalOperationException;
    
}
