/*
 * @file ReservaServiceImp.java;
 * @Autor (c)2024 JuanRuiz
 * @Created 5 mar. 2024,03:11:49
 */
package com.grupouno.hotelnila.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupouno.hotelnila.domain.Cliente;
import com.grupouno.hotelnila.domain.Habitacion;
import com.grupouno.hotelnila.domain.Reserva;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.ErrorMessage;
import com.grupouno.hotelnila.exception.IllegalOperationException;
import com.grupouno.hotelnila.repository.ClienteRepository;
import com.grupouno.hotelnila.repository.HabitacionRepository;
import com.grupouno.hotelnila.repository.ReservaRepository;



/**
 * The Class ReservaServiceImp.
 */
@Service
public class ReservaServiceImp implements ReservaService {

	/** The res rep. */
	@Autowired
	private ReservaRepository resRep;
	
	/** The cli rep. */
	@Autowired
	private ClienteRepository cliRep;
	
	/** The habi rep. */
	@Autowired
	private HabitacionRepository habiRep;

	/**
	 * Listar reservas.
	 *
	 * @return the list
	 */
	@Override
	@Transactional
	public List<Reserva> listarReservas() {
		return (List<Reserva>) resRep.findAll();
	}

	/**
	 * Buscar por id reserva.
	 *
	 * @param idReserva the id reserva
	 * @return the reserva
	 * @throws EntityNotFoundException the entity not found exception
	 */
	@Override
	@Transactional
	public Reserva buscarPorIdReserva(Long idReserva) throws EntityNotFoundException {
		Optional<Reserva> reservas = resRep.findById(idReserva);
        if (reservas.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessage.RESERVA_NOT_FOUND);
        }
        return reservas.get();
	}

	/**
	 * Crear reserva.
	 *
	 * @param reserva the reserva
	 * @return the reserva
	 * @throws IllegalOperationException the illegal operation exception
	 */
	@Override
	@Transactional
	public Reserva crearReserva(Reserva reserva) throws IllegalOperationException {
		return resRep.save(reserva);
	}

	/**
	 * Actualizar reserva.
	 *
	 * @param idReserva the id reserva
	 * @param reserva the reserva
	 * @return the reserva
	 * @throws EntityNotFoundException the entity not found exception
	 * @throws IllegalOperationException the illegal operation exception
	 */
	@Override
	@Transactional
	public Reserva actualizarReserva(Long idReserva, Reserva reserva)
			throws EntityNotFoundException, IllegalOperationException {
		Optional<Reserva> reservaEntity = resRep.findById(idReserva);
        if(reservaEntity.isEmpty()){
            throw new EntityNotFoundException(ErrorMessage.RESERVA_NOT_FOUND);
        }
        
        reserva.setIdReserva(idReserva);
        return resRep.save(reserva);
	}


	/**
	 * Asignar cliente.
	 *
	 * @param idReserva the id reserva
	 * @param idCliente the id cliente
	 * @return the reserva
	 * @throws EntityNotFoundException the entity not found exception
	 * @throws IllegalOperationException the illegal operation exception
	 */
	@Override
	@Transactional
	public Reserva asignarCliente(Long idReserva, Long idCliente)throws 
	EntityNotFoundException, IllegalOperationException {
	Reserva reserva = resRep.findById(idReserva).orElseThrow(
            ()->new EntityNotFoundException(ErrorMessage.RESERVA_NOT_FOUND)
    );

    Cliente cliente = cliRep.findById(idCliente).orElseThrow(
            ()->new EntityNotFoundException(ErrorMessage.CLIENTE_NOT_FOUND)
    );

    // Asignar el cliente a la reserva
    reserva.setCliente(cliente);;

    // Guardar los cambios en la base de datos
    resRep.save(reserva);

    return reserva;
	}

	
	
}
