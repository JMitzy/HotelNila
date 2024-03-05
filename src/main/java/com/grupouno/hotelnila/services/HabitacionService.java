/*
 * @file HabitacionService.java;
 * @Autor (c)2024 JuanRuiz
 * @Created 5 mar. 2024,03:11:10
 */
package com.grupouno.hotelnila.services;

import java.util.List;

import com.grupouno.hotelnila.domain.Habitacion;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.IllegalOperationException;

/**
 * The Interface HabitacionService.
 */
public interface HabitacionService {
	
	/**
	 * Listar habitaciones.
	 *
	 * @return the list
	 */
	List<Habitacion>listarHabitaciones();
	
	/**
	 * Buscar por id habitacion.
	 *
	 * @param idHabitacion the id habitacion
	 * @return the habitacion
	 * @throws EntityNotFoundException the entity not found exception
	 */
	Habitacion buscarPorIdHabitacion(Long idHabitacion) throws EntityNotFoundException;
	
	/**
	 * Crear habitacion.
	 *
	 * @param habitacion the habitacion
	 * @return the habitacion
	 * @throws IllegalOperationException the illegal operation exception
	 */
	Habitacion crearHabitacion (Habitacion habitacion)throws IllegalOperationException;
	
	/**
	 * Actualizar habitacion.
	 *
	 * @param idHabitacion the id habitacion
	 * @param habitacion the habitacion
	 * @return the habitacion
	 * @throws EntityNotFoundException the entity not found exception
	 * @throws IllegalOperationException the illegal operation exception
	 */
	Habitacion actualizarHabitacion(Long idHabitacion, Habitacion habitacion) throws EntityNotFoundException, IllegalOperationException;
}
