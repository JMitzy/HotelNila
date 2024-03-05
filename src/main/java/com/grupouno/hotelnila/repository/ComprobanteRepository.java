/*
 * @file ComprobanteRepository.java;
 * @Autor (c)2024 AndersonDietrich
 * @Created 3 mar. 2024,23:25:28
 */

package com.grupouno.hotelnila.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.grupouno.hotelnila.domain.Comprobante;


/**
 * The Interface FacturaRepository.
 */
public interface ComprobanteRepository extends JpaRepository<Comprobante, Long> {
}
