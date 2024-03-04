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


@Service
public class ReservaServiceImp implements ReservaService {

	@Autowired
	private ReservaRepository resRep;
	
	@Autowired
	private ClienteRepository cliRep;
	
	@Autowired
	private HabitacionRepository habiRep;

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

	@Override
	@Transactional
	public Reserva asignarHabitacion(Long idReserva, Long idHabitacion)throws 
	EntityNotFoundException, IllegalOperationException {
	Reserva reserva = resRep.findById(idReserva).orElseThrow(
            ()->new EntityNotFoundException(ErrorMessage.RESERVA_NOT_FOUND)
    );

    Habitacion habitacion = habiRep.findById(idHabitacion).orElseThrow(
            ()->new EntityNotFoundException(ErrorMessage.HABITACION_NOT_FOUND)
    );

    // Agregar habitacion a la reserva
    reserva.getHabitaciones().add(habitacion);

    // Guardar los cambios en la base de datos
    resRep.save(reserva);

    return reserva;
	}
	
	
}
