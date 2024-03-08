/*
 * @file HabitacionServiceImp.java;
 * @Autor (c)2024 JuanRuiz
 * @Created 5 mar. 2024,03:11:26
 */
package com.grupouno.hotelnila.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.grupouno.hotelnila.domain.Habitacion;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.ErrorMessage;
import com.grupouno.hotelnila.exception.IllegalOperationException;

import com.grupouno.hotelnila.repository.HabitacionRepository;

/**
 * The Class HabitacionServiceImp.
 */
@Service
public class HabitacionServiceImp implements HabitacionService {

	/** The habi rep. */
	@Autowired
	private HabitacionRepository habiRep;
	
	/**
	 * Listar habitaciones.
	 *
	 * @return the list
	 */
	@Override
	@Transactional
	public List<Habitacion> listarHabitaciones() {
		return (List<Habitacion>) habiRep.findAll();
	}

	/**
	 * Buscar por id habitacion.
	 *
	 * @param idHabitacion the id habitacion
	 * @return the habitacion
	 * @throws EntityNotFoundException the entity not found exception
	 */
	@Override
	@Transactional
	public Habitacion buscarPorIdHabitacion(Long idHabitacion) throws EntityNotFoundException {
		Optional<Habitacion> habitaciones = habiRep.findById(idHabitacion);
        if (habitaciones.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessage.HABITACION_NOT_FOUND);
        }
        return habitaciones.get();
	}

	/**
	 * Crear habitacion.
	 *
	 * @param habitacion the habitacion
	 * @return the habitacion
	 * @throws IllegalOperationException the illegal operation exception
	 */
	@Override
	@Transactional
	public Habitacion crearHabitacion(Habitacion habitacion) throws IllegalOperationException {
		if(!habiRep.findBynroHabitacion(habitacion.getNroHabitacion()).isEmpty()) {
		    throw new IllegalOperationException("La habitación ya está registrada.");
		}
		return habiRep.save(habitacion);
	}

	/**
	 * Actualizar habitacion.
	 *
	 * @param idHabitacion the id habitacion
	 * @param habitacion the habitacion
	 * @return the habitacion
	 * @throws EntityNotFoundException the entity not found exception
	 * @throws IllegalOperationException the illegal operation exception
	 */
	@Override
	@Transactional
	public Habitacion actualizarHabitacion(Long idHabitacion, Habitacion habitacion)
			throws EntityNotFoundException, IllegalOperationException {
		Optional<Habitacion> habiEntity = habiRep.findById(idHabitacion);
        if(habiEntity.isEmpty()){
            throw new EntityNotFoundException(ErrorMessage.HABITACION_NOT_FOUND);
        }
        if(!habiRep.findBynroHabitacion(habitacion.getNroHabitacion()).isEmpty()) {
		    throw new IllegalOperationException("La habitación ya está registrada.");
		}
        habitacion.setIdHabitacion(idHabitacion);
        return habiRep.save(habitacion);
	}


	

}
