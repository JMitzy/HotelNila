/*
 * @file ClienteService.java;
 * @Autor (c)2024 JhenniferMendoza
 * @Created 3 mar. 2024,10:50:54
 */
package com.grupouno.hotelnila.services;

import java.util.List;

import com.grupouno.hotelnila.domain.Cliente;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.IllegalOperationException;

/**
 *  
 */
public interface ClienteService {
	
	/**
	* Lista todos los clientes registrados.
	 *
	 * @return Lista de clientes
	 */
	List<Cliente>listarClientes();
    
    /**
     /**
     * Busca un cliente por su ID.
     *
     * @param idCliente ID del cliente a buscar
     * @return El cliente encontrado
     * @throws EntityNotFoundException 
     */
    Cliente buscarPorIdCliente(Long idCliente) throws EntityNotFoundException;
    
    /**
     * Crea un nuevo cliente.
     *
     * @param  El cliente a ser creado
     * @return El cliente creado
     * @throws IllegalOperationException 
     */
    Cliente crearCliente (Cliente cliente)throws IllegalOperationException;
    
    /**
     ** Actualiza la información de un cliente existente.
     *
     * @param  Id del cliente a actualizar
     * @param  Información actualizada del cliente
     * @return El cliente actualizado
     * @throws EntityNotFoundException 
     * @throws IllegalOperationException
     */
    Cliente actualizarCliente(Long idCliente, Cliente cliente) throws EntityNotFoundException, IllegalOperationException;
    
    /**
     * Elimina un cliente.
     *
     * @param  Id del cliente a eliminar
     * @throws EntityNotFoundException 
     * @throws IllegalOperationException
     */
    void eliminarCliente(Long idCliente) throws EntityNotFoundException, IllegalOperationException;
    
    /**
     * Asigna una dirección a un cliente.
     *
     * @param ID del cliente al que se le asignará la dirección
     * @param ID de la dirección a asignar
     * @return El cliente con la dirección asignada
     * @throws EntityNotFoundException 
     * @throws IllegalOperationException 
     */
    Cliente asignarDireccion(Long idCliente, Long idDireccion) throws EntityNotFoundException, IllegalOperationException;

}
