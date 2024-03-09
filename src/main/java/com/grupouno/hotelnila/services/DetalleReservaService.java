/*
 * @file DetalleReservaService.java;
 * @Autor (c)2024 JuanRuiz
 * @Created 5 mar. 2024,03:10:43
 */
package com.grupouno.hotelnila.services;

import java.util.List;

import com.grupouno.hotelnila.domain.DetalleReserva;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.IllegalOperationException;

// TODO: Auto-generated Javadoc
/**
 * Interfaz que define las operaciones de servicio relacionadas con los detalles de reserva.
 */
public interface DetalleReservaService {
	
	/**
	 * Listar detalles reservas.
	 *
	 * @return the list
	 */
	List<DetalleReserva> listarDetallesReservas();
    
    /**
     * Buscar por id detalle reserva.
     *
     * @param idReservaHabitacion the id reserva habitacion
     * @return the detalle reserva
     * @throws EntityNotFoundException the entity not found exception
     */
    DetalleReserva buscarPorIdDetalleReserva(Long idReservaHabitacion) throws EntityNotFoundException;
    
    /**
     * Crear detalle reserva.
     *
     * @param detalleReserva the detalle reserva
     * @return the detalle reserva
     * @throws IllegalOperationException the illegal operation exception
     */
    DetalleReserva crearDetalleReserva (DetalleReserva detalleReserva)throws IllegalOperationException;
    
    /**
     * Actualizar detalle reserva.
     *
     * @param idReservaHabitacion the id reserva habitacion
     * @param detalleReserva the detalle reserva
     * @return the detalle reserva
     * @throws EntityNotFoundException the entity not found exception
     * @throws IllegalOperationException the illegal operation exception
     */
    DetalleReserva actualizarDetalleReserva(Long idReservaHabitacion, DetalleReserva detalleReserva) throws EntityNotFoundException, IllegalOperationException;
    
    /**
     * Asignar reserva.
     *
     * @param idReservaHabitacion the id reserva habitacion
     * @param idReserva the id reserva
     * @return the detalle reserva
     * @throws EntityNotFoundException the entity not found exception
     * @throws IllegalOperationException the illegal operation exception
     */
    DetalleReserva asignarReserva(Long idReservaHabitacion, Long idReserva) throws EntityNotFoundException, IllegalOperationException;
    
    /**
     * Asignar habitacion.
     *
     * @param idReservaHabitacion the id reserva habitacion
     * @param idHabitacion the id habitacion
     * @return the detalle reserva
     * @throws EntityNotFoundException the entity not found exception
     * @throws IllegalOperationException the illegal operation exception
     */
    DetalleReserva asignarHabitacion(Long idReservaHabitacion, Long idHabitacion) throws EntityNotFoundException, IllegalOperationException;
}
