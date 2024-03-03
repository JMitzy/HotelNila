/*
 * @file DireccionService.java;
 * @Autor (c)2024 JhenniferMendoza
 * @Created 3 mar. 2024,11:46:14
 */
package com.grupouno.hotelnila.services;

import java.util.List;

import com.grupouno.hotelnila.domain.Direccion;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.IllegalOperationException;

/**
 * The Interface DireccionService.
 */
public interface DireccionService {
	
	/**
	 * Listar direcciones.
	 *
	 * @return the list
	 */
	List<Direccion>listarDirecciones();
    
    /**
     * Buscar por id direccion.
     *
     * @param idDireccion the id direccion
     * @return the direccion
     * @throws EntityNotFoundException the entity not found exception
     */
    Direccion buscarPorIdDireccion(Long idDireccion) throws EntityNotFoundException;
    
    /**
     * Crear direccion.
     *
     * @param direccion the direccion
     * @return the direccion
     * @throws IllegalOperationException the illegal operation exception
     */
    Direccion crearDireccion (Direccion direccion)throws IllegalOperationException;
    
    /**
     * Actualizar direccion.
     *
     * @param idDireccion the id direccion
     * @param direccion the direccion
     * @return the direccion
     * @throws EntityNotFoundException the entity not found exception
     * @throws IllegalOperationException the illegal operation exception
     */
    Direccion actualizarDireccion(Long idDireccion, Direccion direccion) throws EntityNotFoundException, IllegalOperationException;
    
    /**
     * Eliminar direccion.
     *
     * @param idDireccion the id direccion
     * @throws EntityNotFoundException the entity not found exception
     * @throws IllegalOperationException the illegal operation exception
     */
    void eliminarDireccion(Long idDireccion) throws EntityNotFoundException, IllegalOperationException;
}
