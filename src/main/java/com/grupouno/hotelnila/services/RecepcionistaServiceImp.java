package com.grupouno.hotelnila.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupouno.hotelnila.domain.Recepcionista;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.IllegalOperationException;
import com.grupouno.hotelnila.repository.RecepcionistaRepository;

/**
 * La clase RecepcionistaServiceImpl implementa los métodos de la interfaz RecepcionistaService
 */
@Service
public class RecepcionistaServiceImp implements RecepcionistaService {

	@Autowired
	private RecepcionistaRepository recepcionistaRepository;
	
	@Override
	public List<Recepcionista> listarRecepcionistas() {
		return recepcionistaRepository.findAll();
	}

	@Override
	public Recepcionista buscarPorIdRecepcionista(Long idRecepcionista) throws EntityNotFoundException {
		Optional<Recepcionista> recepcionista = recepcionistaRepository.findById(idRecepcionista);
		if (!recepcionista.isPresent()) {
			throw new EntityNotFoundException("Recepcionista no encontrado");
		}
		return recepcionista.get();
	}

	@Override
	public Recepcionista crearRecepcionista(Recepcionista recepcionista) throws IllegalOperationException {
		// Aquí puedes agregar validaciones antes de guardar el recepcionista
		return recepcionistaRepository.save(recepcionista);
	}

	@Override
	public Recepcionista actualizarRecepcionista(Long idRecepcionista, Recepcionista recepcionista) throws EntityNotFoundException, IllegalOperationException {
		// Aquí puedes agregar validaciones antes de actualizar el recepcionista
		if (!recepcionistaRepository.existsById(idRecepcionista)) {
			throw new EntityNotFoundException("Recepcionista no encontrado");
		}
		recepcionista.setIdRecepcionista(idRecepcionista);
		return recepcionistaRepository.save(recepcionista);
	}

	@Override
	public void eliminarRecepcionista(Long idRecepcionista) throws EntityNotFoundException, IllegalOperationException {
		if (!recepcionistaRepository.existsById(idRecepcionista)) {
			throw new EntityNotFoundException("Recepcionista no encontrado");
		}
		recepcionistaRepository.deleteById(idRecepcionista);
	}

	@Override
	public Recepcionista asignarReserva(Long idRecepcionista, Long idReserva) throws EntityNotFoundException, IllegalOperationException {
		// Aquí puedes agregar la lógica para asignar una reserva a un recepcionista
		throw new UnsupportedOperationException("Método no implementado");
	}
	
}
