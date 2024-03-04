package com.grupouno.hotelnila.services;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupouno.hotelnila.domain.Cliente;
import com.grupouno.hotelnila.domain.Habitacion;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.ErrorMessage;
import com.grupouno.hotelnila.exception.IllegalOperationException;
import com.grupouno.hotelnila.repository.ClienteRepository;
import com.grupouno.hotelnila.repository.HabitacionRepository;

@Service
public class HabitacionServiceImp implements HabitacionService {

	@Autowired
	private HabitacionRepository habiRep;
	
	@Override
	@Transactional
	public List<Habitacion> listarHabitaciones() {
		return (List<Habitacion>) habiRep.findAll();
	}

	@Override
	@Transactional
	public Habitacion buscarPorIdHabitacion(Long idHabitacion) throws EntityNotFoundException {
		Optional<Habitacion> habitaciones = habiRep.findById(idHabitacion);
        if (habitaciones.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessage.HABITACION_NOT_FOUND);
        }
        return habitaciones.get();
	}

	@Override
	@Transactional
	public Habitacion crearHabitacion(Habitacion habitacion) throws IllegalOperationException {
		if(!habiRep.findBynroHabitacion(habitacion.getNroHabitacion()).isEmpty()) {
		    throw new IllegalOperationException("La habitación ya está registrada.");
		}
		return habiRep.save(habitacion);
	}

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
