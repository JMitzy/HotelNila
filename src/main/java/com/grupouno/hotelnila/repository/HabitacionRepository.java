package com.grupouno.hotelnila.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupouno.hotelnila.domain.Habitacion;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {

	List<Habitacion> findBynroHabitacion(String nroHabitacion);
}
