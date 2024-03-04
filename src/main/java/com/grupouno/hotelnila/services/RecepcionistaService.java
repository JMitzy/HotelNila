/*
 * @file RecepcionistaService.java;
 * @Autor (c)2024 ErickBecerra
 * @Created 3 mar. 2024,16:20:59
 */


package com.grupouno.hotelnila.services;

import java.util.List;

import com.grupouno.hotelnila.domain.Recepcionista;
import com.grupouno.hotelnila.domain.Reserva;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.IllegalOperationException;

/**
 * La interfaz RecepcionistaService proporciona métodos para interactuar con la entidad Recepcionista
 */
public interface RecepcionistaService {

	/**
	* Lista todos los recepcionistas registrados.
	 *
	 * @return Lista de recepcionistas
	 */
	List<Recepcionista> listarRecepcionistas();
    
    /**
     * Busca un recepcionista por su ID.
     *
     * @param idRecepcionista ID del recepcionista a buscar
     * @return El recepcionista encontrado
     * @throws EntityNotFoundException 
     */
    Recepcionista buscarPorIdRecepcionista(Long idRecepcionista) throws EntityNotFoundException;
    
    /**
     * Crea un nuevo recepcionista.
     *
     * @param  El recepcionista a ser creado
     * @return El recepcionista creado
     * @throws IllegalOperationException 
     */
    Recepcionista crearRecepcionista (Recepcionista recepcionista) throws IllegalOperationException;
    
    /**
     ** Actualiza la información de un recepcionista existente.
     *
     * @param  Id del recepcionista a actualizar
     * @param  Información actualizada del recepcionista
     * @return El recepcionista actualizado
     * @throws EntityNotFoundException 
     * @throws IllegalOperationException
     */
    Recepcionista actualizarRecepcionista(Long idRecepcionista, Recepcionista recepcionista) throws EntityNotFoundException, IllegalOperationException;
    
    /**
     * Elimina un recepcionista.
     *
     * @param  Id del recepcionista a eliminar
     * @throws EntityNotFoundException 
     * @throws IllegalOperationException
     */
    void eliminarRecepcionista(Long idRecepcionista) throws EntityNotFoundException, IllegalOperationException;
    
    /**
     * Asigna una reserva a un recepcionista.
     *
     * @param ID del recepcionista al que se le asignará la reserva
     * @param ID de la reserva a asignar
     * @return El recepcionista con la reserva asignada
     * @throws EntityNotFoundException 
     * @throws IllegalOperationException
     */
    Recepcionista asignarReserva(Long idRecepcionista, Long idReserva) throws EntityNotFoundException, IllegalOperationException;
	
    /**
     * Obtiene las reservas de un recepcionista.
     *
     * @param idRecepcionista ID del recepcionista
     * @return Lista de reservas del recepcionista
     * @throws EntityNotFoundException 
     */
    List<Reserva> obtenerReservas(Long idRecepcionista) throws EntityNotFoundException;
}
