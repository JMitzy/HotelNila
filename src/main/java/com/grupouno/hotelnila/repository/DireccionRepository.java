/*
 * @file DireccionRepository.java;
 * @Autor (c)2024 JhenniferMendoza
 * @Created 3 mar. 2024,10:57:18
 */
package com.grupouno.hotelnila.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.grupouno.hotelnila.domain.Direccion;

/**
 * La interfaz DireccionRepository proporciona métodos para interactuar con la entidad Direccion.
 */
public interface DireccionRepository extends CrudRepository<Direccion, Long> {
	
	/**
	  *  * Busca direcciones por su nombre.
	 *
	 * @param nombre de la dirección a buscar
	 */
	List<Direccion> findByNombreDireccion(String nombreDireccion);
}
