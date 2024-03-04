package com.grupouno.hotelnila.services;

import java.util.List;
import com.grupouno.hotelnila.domain.Reserva;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.IllegalOperationException;

public interface ReservaService {
	List<Reserva>listarReservas();
	Reserva buscarPorIdReserva(Long idReserva) throws EntityNotFoundException;
	Reserva crearReserva (Reserva reserva)throws IllegalOperationException;
	Reserva actualizarReserva(Long idReserva, Reserva reserva) throws EntityNotFoundException, IllegalOperationException;
    Reserva asignarCliente(Long idReserva, Long idCliente) throws EntityNotFoundException, IllegalOperationException;
    Reserva asignarHabitacion(Long idReserva, Long idHabitacion) throws EntityNotFoundException, IllegalOperationException;
    
}
