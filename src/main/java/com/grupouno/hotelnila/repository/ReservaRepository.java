/*
 * @file ReservaRepository.java;
 * @Autor (c)2024 JuanRuiz
 * @Created 3 mar. 2024,10:59:11
 */
package com.grupouno.hotelnila.repository;

import org.springframework.data.repository.CrudRepository;

import com.grupouno.hotelnila.domain.Reserva;

/**
 * The Interface ReservaRepository.
 */
public interface ReservaRepository extends CrudRepository<Reserva, Long> {
}
