/*
 * @file RecepcionistaServiceImp.java;
 * @Autor (c)2024 ErickBecerra
 * @Created 5 mar. 2024,00:04:01
 */


package com.grupouno.hotelnila.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupouno.hotelnila.domain.Recepcionista;
import com.grupouno.hotelnila.domain.Reserva;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.ErrorMessage;
import com.grupouno.hotelnila.exception.IllegalOperationException;
import com.grupouno.hotelnila.repository.RecepcionistaRepository;
import com.grupouno.hotelnila.repository.ReservaRepository;

// TODO: Auto-generated Javadoc
/**
 * La clase RecepcionistaServiceImpl implementa los métodos de la interfaz RecepcionistaService.
 */
@Service
public class RecepcionistaServiceImp implements RecepcionistaService {

	 /** El repositorio del recepcionista. */
    @Autowired
    private RecepcionistaRepository recepcionistaRep;
    
    /** El repositorio de la reserva. */
    @Autowired
    private ReservaRepository reservaRep;

	 /**
     * Listar recepcionistas.
     *
     * @return la lista de recepcionistas
     */
	@Override
	@Transactional
	public List<Recepcionista> listarRecepcionistas() {
		return recepcionistaRep.findAll();
	}

	/**
     * Buscar por id recepcionista.
     *
     * @param idRecepcionista el id del recepcionista
     * @return el recepcionista
     * @throws EntityNotFoundException si no se encuentra el recepcionista
     */
	@Override
	@Transactional
	public Recepcionista buscarPorIdRecepcionista(Long idRecepcionista) throws EntityNotFoundException {
		Optional<Recepcionista> recepcionista = recepcionistaRep.findById(idRecepcionista);
		if (!recepcionista.isPresent()) {
			throw new EntityNotFoundException(ErrorMessage.RECEPCIONISTA_NOT_FOUND);
		}
		return recepcionista.get();
	}

	 /**
     * Crear recepcionista.
     *
     * @param recepcionista el recepcionista a crear
     * @return el recepcionista creado
     * @throws IllegalOperationException si ocurre una operación ilegal
     */
	@Override
	@Transactional
	public Recepcionista crearRecepcionista(Recepcionista recepcionista) throws IllegalOperationException {
		List<Recepcionista> recepcionistasMismoTurno = recepcionistaRep.findByTurno(recepcionista.getTurno());
		if(!recepcionistasMismoTurno.isEmpty()){
			throw new IllegalOperationException("Ya existe un recepcionista asignado para el turno " + recepcionista.getTurno() + ".");
		}
		return recepcionistaRep.save(recepcionista);
	}

	 /**
     * Actualizar recepcionista.
     *
     * @param idRecepcionista el id del recepcionista a actualizar
     * @param recepcionista el recepcionista con los datos actualizados
     * @return el recepcionista actualizado
     * @throws EntityNotFoundException si no se encuentra el recepcionista
     * @throws IllegalOperationException si ocurre una operación ilegal
     */
	@Override
	@Transactional
	public Recepcionista actualizarRecepcionista(Long idRecepcionista, Recepcionista recepcionista) throws EntityNotFoundException, IllegalOperationException {
		Optional<Recepcionista> recepcionistaEntity = recepcionistaRep.findById(idRecepcionista);
		if(recepcionistaEntity.isEmpty()){
			throw new EntityNotFoundException(ErrorMessage.RECEPCIONISTA_NOT_FOUND);
		}
		List<Recepcionista> recepcionistasMismoTurno = recepcionistaRep.findByTurno(recepcionista.getTurno());
		if(!recepcionistasMismoTurno.isEmpty() && !recepcionistasMismoTurno.get(0).getIdRecepcionista().equals(idRecepcionista)){
			throw new IllegalOperationException("Ya existe un recepcionista asignado para el turno " + recepcionista.getTurno() + ".");
		}
		recepcionista.setIdRecepcionista(idRecepcionista);
		return recepcionistaRep.save(recepcionista);
	}

	 /**
     * Eliminar recepcionista.
     *
     * @param idRecepcionista el id del recepcionista a eliminar
     * @throws EntityNotFoundException si no se encuentra el recepcionista
     * @throws IllegalOperationException si ocurre una operación ilegal
     */
	@Override
	@Transactional
	public void eliminarRecepcionista(Long idRecepcionista) throws EntityNotFoundException, IllegalOperationException {
		Recepcionista recepcionista = recepcionistaRep.findById(idRecepcionista).orElseThrow(
				()->new EntityNotFoundException(ErrorMessage.RECEPCIONISTA_NOT_FOUND)
				);

		if(!recepcionista.getReservas().isEmpty()){
			throw new IllegalOperationException("El recepcionista tiene reservas asignadas");
		}
		recepcionistaRep.deleteById(idRecepcionista);
	}


	/**
     * Asignar reserva.
     *
     * @param idRecepcionista el id del recepcionista
     * @param idReserva el id de la reserva
     * @return el recepcionista
     * @throws EntityNotFoundException si no se encuentra el recepcionista o la reserva
     * @throws IllegalOperationException si ocurre una operación ilegal
     */
	@Override
	@Transactional
	public Recepcionista asignarReserva(Long idRecepcionista, Long idReserva) throws EntityNotFoundException, IllegalOperationException {
		Recepcionista recepcionista = recepcionistaRep.findById(idRecepcionista).orElseThrow(
				()->new EntityNotFoundException(ErrorMessage.RECEPCIONISTA_NOT_FOUND)
				);
		Reserva reserva = reservaRep.findById(idReserva).orElseThrow(
				()->new EntityNotFoundException(ErrorMessage.RESERVA_NOT_FOUND)
				);
		if(reserva.getRecepcionista() != null){
			throw new IllegalOperationException("La reserva ya está asignada a un recepcionista");
		}
		reserva.setRecepcionista(recepcionista);
		reservaRep.save(reserva);
		return recepcionista;
	}

	/**
	 * Obtener reservas.
	 *
	 * @param idRecepcionista the id recepcionista
	 * @return the list
	 * @throws EntityNotFoundException the entity not found exception
	 */
	public List<Reserva> obtenerReservas(Long idRecepcionista) throws EntityNotFoundException {
		Recepcionista recepcionista = recepcionistaRep.findById(idRecepcionista).orElseThrow(
				()->new EntityNotFoundException(ErrorMessage.RECEPCIONISTA_NOT_FOUND)
				);
		return recepcionista.getReservas();
	}

}
