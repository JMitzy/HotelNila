/*
 * @file ClienteRepository.java;
 * @Autor (c)2024 JhenniferMendoza
 * @Created 3 mar. 2024,10:57:24
 */
package com.grupouno.hotelnila.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.grupouno.hotelnila.domain.Cliente;


/**
 * La interfaz ClienteRepository proporciona métodos para interactuar con la entidad Cliente
 */
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
	
	/**
	 *  * Busca clientes por su número de DNI.
	 *
	 * @param dni del cliente a buscar
	 */
	List<Cliente> findBydni(String dniCli);
}
