/*
 * @file DireccionRepository.java;
 * @Autor (c)2024 JhenniferMendoza
 * @Created 3 mar. 2024,10:57:18
 */
package com.grupouno.hotelnila.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.grupouno.hotelnila.domain.Direccion;

/**
 * La interfaz DireccionRepository proporciona métodos para interactuar con la entidad Direccion.
 */
public interface DireccionRepository extends JpaRepository<Direccion, Long> {
	
	/**
	  *  * Busca direcciones por su nombre.
	 *
	 * @param nombre de la dirección a buscar
	 */
	List<Direccion> findBynombreDirec(String nombreDireccion);
}
