/*
 * @file DetalleReservaRepository.java;
 * @Autor (c)2024 JuanRuiz
 * @Created 5 mar. 2024,03:09:42
 */
package com.grupouno.hotelnila.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.grupouno.hotelnila.domain.DetalleReserva;

/**
 * The Interface DetalleReservaRepository.
 */
public interface DetalleReservaRepository extends JpaRepository<DetalleReserva,Long> {
}

