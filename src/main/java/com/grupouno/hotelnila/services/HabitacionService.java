package com.grupouno.hotelnila.services;

import java.util.List;

import com.grupouno.hotelnila.domain.Habitacion;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.IllegalOperationException;

public interface HabitacionService {
	List<Habitacion>listarHabitaciones();
	Habitacion buscarPorIdHabitacion(Long idHabitacion) throws EntityNotFoundException;
	Habitacion crearHabitacion (Habitacion habitacion)throws IllegalOperationException;
	Habitacion actualizarHabitacion(Long idHabitacion, Habitacion habitacion) throws EntityNotFoundException, IllegalOperationException;
}
