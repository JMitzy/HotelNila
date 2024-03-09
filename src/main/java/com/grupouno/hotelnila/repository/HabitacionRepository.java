/*
 * @file HabitacionRepository.java;
 * @Autor (c)2024 JuanRuiz
 * @Created 5 mar. 2024,03:10:24
 */
package com.grupouno.hotelnila.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupouno.hotelnila.domain.Habitacion;

// TODO: Auto-generated Javadoc
/**
 * The Interface HabitacionRepository.
 */
public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {

	/**
	 * Find bynro habitacion.
	 *
	 * @param nroHabitacion the nro habitacion
	 * @return the list
	 */
	List<Habitacion> findBynroHabitacion(String nroHabitacion);
}
