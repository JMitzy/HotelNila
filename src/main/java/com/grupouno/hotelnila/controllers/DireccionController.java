/*
 * @file DireccionController.java;
 * @Autor (c)2024 JhenniferMendoza
 * @Created 3 mar. 2024,12:55:16
 */
package com.grupouno.hotelnila.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.grupouno.hotelnila.domain.Direccion;
import com.grupouno.hotelnila.dto.DireccionDTO;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.IllegalOperationException;
import com.grupouno.hotelnila.services.DireccionService;
import com.grupouno.hotelnila.util.ApiResponse;

import jakarta.validation.Valid;


/**
 * La clase DireccionController proporciona endpoints para operaciones relacionadas con direcciones.
 */
@RestController
@RequestMapping("/api/direcciones")
public class DireccionController {

	@Autowired
    private DireccionService direcService;

    /** ModelMapper para mapeo de DTOs.  */
    @Autowired
    private ModelMapper modelMapper;

	/**
	* Obtiene una lista de todos las direcciones.
     *
     * @return ResponseEntity con la lista de direcciones y un mensaje de éxito
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
	  * Obtiene una dirección por su ID.
     *
     * @param ID de la dirección a buscar
     * @return ResponseEntity con la direccion encontrada y un mensaje de éxito
     * @throws EntityNotFoundException
	 */
	@GetMapping("/{idDireccion}")
    public ResponseEntity<?> listarPorID(@PathVariable Long idDireccion) throws EntityNotFoundException {
        Direccion direcciones = direcService.buscarPorIdDireccion(idDireccion);
        DireccionDTO direccionDTO = modelMapper.map(direcciones, DireccionDTO.class);
        ApiResponse<DireccionDTO> response = new ApiResponse<>(true, "Dirección obtenida con éxito", direccionDTO);
        return ResponseEntity.ok(response);
    }
	
	/**
	 * Crea una nueva dirección.
     *
     * @param DTO de la dirección a crear
     * @return ResponseEntity con la dirección creada y un mensaje de éxito
     * @throws IllegalOperationException
	 */
	@PostMapping
    public ResponseEntity<?> crearDireccion(@Valid @RequestBody DireccionDTO direccionDTO,BindingResult result) throws IllegalOperationException {
		if(result.hasErrors()) {
        	return validar(result);
        }
		Direccion direccion = modelMapper.map(direccionDTO, Direccion.class);
        direcService.crearDireccion(direccion);
        DireccionDTO savedDireccionDTO = modelMapper.map(direccion, DireccionDTO.class);
        ApiResponse<DireccionDTO> response = new ApiResponse<>(true, "Dirección creada con éxito", savedDireccionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
	
	/**
	 * Actualizar direccion.
	 *
	 * @param id de la dirección
	 * @param Información actualizada de la dirección
	 * @return Dirección actualizada
	 * @throws EntityNotFoundException 
	 * @throws IllegalOperationException 
	 */
	@PutMapping("/{idDireccion}")
    public ResponseEntity<?> actualizarDireccion(@Valid @RequestBody DireccionDTO direccionDTO,BindingResult result, @PathVariable Long idDireccion) throws EntityNotFoundException, IllegalOperationException {
		Direccion direccion = modelMapper.map(direccionDTO, Direccion.class);
        direcService.actualizarDireccion(idDireccion,direccion);
        DireccionDTO updatedDireccionDTO = modelMapper.map(direccion, DireccionDTO.class);
        ApiResponse<DireccionDTO> response = new ApiResponse<>(true, "Direccion actualizada con éxito",updatedDireccionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    } 
	
	/**
	   * Elimina una direccion por su ID.
     *
     * @param  ID de la dirección a eliminar
     * @return ResponseEntity con un mensaje de éxito
     * @throws EntityNotFoundException 
     * @throws IllegalOperationException 
	 */
	@DeleteMapping("/{idDireccion}")
    public ResponseEntity<?> eliminarDireccion(@PathVariable Long idDireccion) throws EntityNotFoundException, IllegalOperationException {
		direcService.eliminarDireccion(idDireccion);
        ApiResponse<String> response = new ApiResponse<>(true, "Direccion eliminada con éxito", null);
        return ResponseEntity.ok(response);
    }
	
	 private ResponseEntity<Map<String, String>> validar(BindingResult result) {
	        Map<String, String> errores = new HashMap<>();
	        result.getFieldErrors().forEach(err -> {
	            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
	        });
	        return ResponseEntity.badRequest().body(errores);
	    }
}


