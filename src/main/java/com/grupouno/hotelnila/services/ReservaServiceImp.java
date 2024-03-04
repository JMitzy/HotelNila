package com.grupouno.hotelnila.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupouno.hotelnila.domain.Reserva;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.ErrorMessage;
import com.grupouno.hotelnila.exception.IllegalOperationException;
import com.grupouno.hotelnila.repository.ReservaRepository;


@Service
public class ReservaServiceImp implements ReservaService {

	@Autowired
	private ReservaRepository resRep;

	@Override
	@Transactional
	public List<Reserva> listarReservas() {
		return (List<Reserva>) resRep.findAll();
	}

	@Override
	@Transactional
	public Reserva buscarPorIdReserva(Long idReserva) throws EntityNotFoundException {
		Optional<Reserva> reservas = resRep.findById(idReserva);
        if (reservas.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessage.RESERVA_NOT_FOUND);
        }
        return reservas.get();
	}

	@Override
	@Transactional
	public Reserva crearReserva(Reserva reserva) throws IllegalOperationException {
		return resRep.save(reserva);
	}

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


	@Override
	@Transactional
	public Reserva asignarCliente(Long idReserva, Long idCliente)
			throws EntityNotFoundException, IllegalOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Reserva asignarHabitacion(Long idReserva, Long idHabitacion)
			throws EntityNotFoundException, IllegalOperationException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
