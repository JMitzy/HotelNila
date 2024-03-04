/*
 * @file FacturaService.java;
 * @Autor (c)2024 AndersonDietrich
 * @Created 3 mar. 2024,19:14:05
 */
package com.grupouno.hotelnila.services;

import java.util.List;

import com.grupouno.hotelnila.domain.Factura;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.IllegalOperationException;

// TODO: Auto-generated Javadoc
/**
 * The Interface FacturaService.
 */
public interface FacturaService {
	/**
	* Lista todas los facturas registradas.
	 *
	 * @return Lista de facturas
	 */
	List<Factura>listarFacturas();
	
    /**
    /**
    * Busca una factura por su ID.
    *
    * @param IdFactura ID de la factura a buscar
    * @return La Factura encontrada
    * @throws EntityNotFoundException 
    */
	Factura buscarPorIdFactura(Long idFactura) throws EntityNotFoundException;
	
    /**
     * Crea un nueva Factura.
     *
     * @param  La factura a ser creada
     * @return La factura creada
     * @throws IllegalOperationException 
     */
	
	Factura crearFactura(Factura factura) throws IllegalOperationException;
	
    /**
     ** Actualiza la información de una factura existente.
     *
     * @param  Id de la factura a actualizar
     * @param  Información actualizada de la factura
     * @return La factura actualizada
     * @throws EntityNotFoundException 
     * @throws IllegalOperationException
     */
	
	Factura actualizarFactura(Long idFactura, Factura factura)throws EntityNotFoundException, IllegalOperationException;
	
    /**
     * Elimina una factura.
     *
     * @param  Id de la factura a eliminar
     * @throws EntityNotFoundException 
     * @throws IllegalOperationException
     */
	
	void eliminarFactura(Long idFactura)throws EntityNotFoundException, IllegalOperationException;
	
    /**
    /**
    * Asigna una reserva a una factura.
    *
    * @param ID de la factura al que se le asignará la reserva
    * @param ID de la reserva a asignar
    * @return La factura con la reserva asignada
    * @throws EntityNotFoundException 
    * @throws IllegalOperationException
    */
	
	Factura asignarReserva(Long idFactura, Long idReserva)throws EntityNotFoundException, IllegalOperationException;
	
}
