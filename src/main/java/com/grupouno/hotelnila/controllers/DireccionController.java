/*
 * @file DireccionController.java;
 * @Autor (c)2024 JhenniferMendoza
 * @Created 3 mar. 2024,12:55:16
 */
package com.grupouno.hotelnila.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupouno.hotelnila.domain.Direccion;
import com.grupouno.hotelnila.dto.DireccionDTO;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.IllegalOperationException;
import com.grupouno.hotelnila.services.DireccionService;
import com.grupouno.hotelnila.util.ApiResponse;


/**
 * La clase DireccionController proporciona endpoints para operaciones relacionadas con clientes.
 */
@RestController
@RequestMapping("/api/direcciones")
public class DireccionController {
	/** The cliente service. */
	@Autowired
    private DireccionService direcService;

    /** The model mapper. */
    @Autowired
    private ModelMapper modelMapper;

	/**
	 * Listar clientes.
	 *
	 * @return the response entity
	 */
	@GetMapping
    public ResponseEntity<?> listarDirecciones(){
        List<Direccion> direcciones = direcService.listarDirecciones();
        List<DireccionDTO> direcDTOs = direcciones.stream().map(direccion->modelMapper.map(direccion, DireccionDTO.class))
                .collect(Collectors.toList());
        ApiResponse<List<DireccionDTO>> response = new ApiResponse<>(true, "Lista de direcciones obtenida con éxito", direcDTOs);
        return ResponseEntity.ok(response);
    }
	
	
	/**
	 * Listar por ID.
	 *
	 * @param idDireccion the id direccion
	 * @return the response entity
	 * @throws EntityNotFoundException the entity not found exception
	 */
	@GetMapping("/{idDireccion}")
    public ResponseEntity<?> listarPorID(@PathVariable Long idDireccion) throws EntityNotFoundException {
        Direccion direcciones = direcService.buscarPorIdDireccion(idDireccion);
        DireccionDTO direccionDTO = modelMapper.map(direcciones, DireccionDTO.class);
        ApiResponse<DireccionDTO> response = new ApiResponse<>(true, "Dirección obtenida con éxito", direccionDTO);
        return ResponseEntity.ok(response);
    }
	
	/**
	 * Crear cliente.
	 *
	 * @param direccionDTO the direccion DTO
	 * @return the response entity
	 * @throws IllegalOperationException the illegal operation exception
	 */
	@PostMapping
    public ResponseEntity<?> crearCliente(@RequestBody DireccionDTO direccionDTO) throws IllegalOperationException {
        Direccion direccion = modelMapper.map(direccionDTO, Direccion.class);
        direcService.crearDireccion(direccion);
        DireccionDTO savedDireccionDTO = modelMapper.map(direccion, DireccionDTO.class);
        ApiResponse<DireccionDTO> response = new ApiResponse<>(true, "Dirección creada con éxito", savedDireccionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
	
	/**
	 * Eliminar cliente.
	 *
	 * @param idDireccion the id direccion
	 * @return the response entity
	 * @throws EntityNotFoundException the entity not found exception
	 * @throws IllegalOperationException the illegal operation exception
	 */
	@DeleteMapping("/{idDireccion}")
    public ResponseEntity<?> eliminarDireccion(@PathVariable Long idDireccion) throws EntityNotFoundException, IllegalOperationException {
		direcService.eliminarDireccion(idDireccion);
        ApiResponse<String> response = new ApiResponse<>(true, "Direccion eliminada con éxito", null);
        return ResponseEntity.ok(response);
    }
}


