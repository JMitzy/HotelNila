/*
 * @file ComprobanteService.java;
 * @Autor (c)2024 AndersonDietrich
 * @Created 3 mar. 2024,19:14:05
 */
package com.grupouno.hotelnila.services;

import java.util.List;

import com.grupouno.hotelnila.domain.Comprobante;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.IllegalOperationException;


/**
 * Interfaz que define las operaciones de servicio relacionadas con los comprobantes.
 */
public interface ComprobanteService {
	/**
	* Lista todos los comprobantes registrados.
	 *
	 * @return Lista de comprobantes
	 */
	List<Comprobante>listarComprobantes();
	
    /**
    /**
    * Busca un comprobante por su ID.
    *
    * @param IdComprobante ID del comprobante a buscar
    * @return Comprobante encontrado
    * @throws EntityNotFoundException 
    */
	Comprobante buscarPorIdComprobante(Long idComprobante) throws EntityNotFoundException;
	
    /**
     * Crea un nuevo comprobante
     *
     * @param Comprobante a ser creado
     * @return El comprobante creado
     * @throws IllegalOperationException 
     */
	
	Comprobante crearComprobante(Comprobante comprobante) throws IllegalOperationException;
	
    /**
     ** Actualiza la información de comprobante existente.
     *
     * @param  Comprobante a actualizar
     * @param  Información actualizada del comprobante
     * @return El comprobante actualizado
     * @throws EntityNotFoundException 
     * @throws IllegalOperationException
     */
	
	Comprobante actualizarComprobante(Long idComprobante, Comprobante comprobante)throws EntityNotFoundException, IllegalOperationException;
	
    /**
     * Elimina un comprobante.
     *
     * @param  Id del comprobante a eliminar
     * @throws EntityNotFoundException 
     * @throws IllegalOperationException
     */
	
	void eliminarComprobante(Long idComprobante)throws EntityNotFoundException, IllegalOperationException;
	
    /**
    /**
    * Asigna una reserva a un comprobante.
    *
    * @param ID del comprobante al que se le asignará la reserva
    * @param ID de la reserva a asignar
    * @return comprobante con la reserva asignada
    * @throws EntityNotFoundException 
    * @throws IllegalOperationException
    */
	
	Comprobante asignarReserva(Long idComprobante, Long idReserva)throws EntityNotFoundException, IllegalOperationException;
}
