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

/**
 * La clase RecepcionistaServiceImpl implementa los métodos de la interfaz RecepcionistaService
 */
@Service
public class RecepcionistaServiceImp implements RecepcionistaService {

	@Autowired
	private RecepcionistaRepository recepcionistaRep;
	@Autowired
	private ReservaRepository reservaRep;
	
	@Override
	@Transactional
	public List<Recepcionista> listarRecepcionistas() {
		return recepcionistaRep.findAll();
	}

	@Override
	@Transactional
	public Recepcionista buscarPorIdRecepcionista(Long idRecepcionista) throws EntityNotFoundException {
		Optional<Recepcionista> recepcionista = recepcionistaRep.findById(idRecepcionista);
		if (!recepcionista.isPresent()) {
			throw new EntityNotFoundException(ErrorMessage.RECEPCIONISTA_NOT_FOUND);
		}
		return recepcionista.get();
	}

	@Override
	@Transactional
	public Recepcionista crearRecepcionista(Recepcionista recepcionista) throws IllegalOperationException {
	    List<Recepcionista> recepcionistasMismoTurno = recepcionistaRep.findByTurno(recepcionista.getTurno());
	    if(!recepcionistasMismoTurno.isEmpty()){
	        throw new IllegalOperationException("Ya existe un recepcionista asignado para el turno " + recepcionista.getTurno() + ".");
	    }
	    return recepcionistaRep.save(recepcionista);
	}

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

	@Override
	@Transactional
	public Recepcionista asignarReserva(Long idRecepcionista, Long idReserva) throws EntityNotFoundException, IllegalOperationException {
	    Recepcionista recepcionista = recepcionistaRep.findById(idRecepcionista).orElseThrow(
	            ()->new EntityNotFoundException("Recepcionista no encontrado")
	    );
	    Reserva reserva = reservaRep.findById(idReserva).orElseThrow(
	            ()->new EntityNotFoundException("Reserva no encontrada")
	    );
	    if(reserva.getRecepcionista() != null){
	        throw new IllegalOperationException("La reserva ya está asignada a un recepcionista");
	    }
	    reserva.setRecepcionista(recepcionista);
	    reservaRep.save(reserva);
	    return recepcionista;
	}

	
}
