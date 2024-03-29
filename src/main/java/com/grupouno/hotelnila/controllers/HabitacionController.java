/*
 * @file HabitacionController.java;
 * @Autor (c)2024 JhenniferMendoza
 * @Created 8 mar. 2024,17:38:35
 */
package com.grupouno.hotelnila.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.grupouno.hotelnila.domain.Habitacion;

import com.grupouno.hotelnila.dto.ClienteDTO;

import com.grupouno.hotelnila.dto.HabitacionDTO;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.IllegalOperationException;
import com.grupouno.hotelnila.services.HabitacionService;
import com.grupouno.hotelnila.util.ApiResponse;

import jakarta.validation.Valid;


/**
 * Controlador REST para gestionar operaciones relacionadas con las habitaciones.
 */
@RestController
@RequestMapping("/api/habitaciones")
public class HabitacionController {
	
	@Autowired
    private HabitacionService habiService;

 
    @Autowired
    private ModelMapper modelMapper;

	/**
	 * Obtiene una lista de todas las habitaciones.
     *
     * @return ResponseEntity con la lista de habitaciones y un mensaje de exito
	 */   
	@GetMapping(headers = "X-API-VERSION=1.0.0")
    public ResponseEntity<?> listarHabitaciones(){
        List<Habitacion> habitaciones = habiService.listarHabitaciones();
        List<HabitacionDTO> habitacionDTOs = habitaciones.stream().map(habitacion->modelMapper.map(habitacion, HabitacionDTO.class))
                .collect(Collectors.toList());

       
     // Crear enlace al recurso de la colección de clientes
        WebMvcLinkBuilder linkToHabitaciones = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).listarHabitaciones());
        CollectionModel<HabitacionDTO> habitacionCollectionModel = CollectionModel.of(habitacionDTOs);
        habitacionCollectionModel.add(linkToHabitaciones.withSelfRel());
        
        ApiResponse<CollectionModel<HabitacionDTO>> response = new ApiResponse<>(true, "Lista de clientes obtenida con éxito",
        		habitacionCollectionModel);

        return ResponseEntity.ok(response);
    }
	
	
	/**
	 * Obtiene una habitacion por su ID.
	 *
	 * @param idHabitacion El Id de la habitacion que se desea obtener.
	 * @return ResponseEntity con la habitación obtenida y un mensaje de exito, o una respuesta de error si no se encuentra la habitacion.
	 * @throws EntityNotFoundException Si no se encuentra la habitacion con el ID proporcionado.
	 */
	@GetMapping(value="/{idHabitacion}", headers = "X-API-VERSION=1.0.0")
    public ResponseEntity<?> listarPorID(@PathVariable Long idHabitacion) throws EntityNotFoundException {
		Habitacion habitaciones = habiService.buscarPorIdHabitacion(idHabitacion);
		HabitacionDTO habitacionDTO = modelMapper.map(habitaciones, HabitacionDTO.class);

		// Crear enlace al recurso cliente
	    EntityModel<HabitacionDTO> resource = EntityModel.of(habitacionDTO);
	    WebMvcLinkBuilder linkToHabitacion = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).listarPorID(idHabitacion));
	    resource.add(linkToHabitacion.withSelfRel());

	    // Agregar mensajes para verificar la generación del enlace
	    if (resource.hasLinks()) {
	        System.out.println("Enlace generado correctamente para el cliente con ID: " + idHabitacion);
	    } else {
	        System.out.println("Error al generar el enlace para el cliente con ID: " + idHabitacion);
	    }

	    return ResponseEntity.ok(resource);
	}

	/**
	 * Crea una nueva habitación.
	 *
	 * @param habitacionDTO El DTO de la habitacion que se desea crear
	 * @param result El resultado de la validacion de entrada
	 * @return ResponseEntity con la habitación creada y un mensaje de exito, o una respuesta de error si hay errores de validacion.
	 * @throws IllegalOperationException Si ocurre un error durante la operación de creación de la habitacion
	 */
	@PostMapping(headers = "X-API-VERSION=1.0.0")
    public ResponseEntity<?> crearHabitacion(@RequestBody HabitacionDTO habitacionDTO, BindingResult result) throws IllegalOperationException {
		if(result.hasErrors()) {
			return validar(result);
		}
		Habitacion habitacion = modelMapper.map(habitacionDTO, Habitacion.class);
        habiService.crearHabitacion(habitacion);
        HabitacionDTO savedHabitacionDTO = modelMapper.map(habitacion, HabitacionDTO.class);
        ApiResponse<HabitacionDTO> response = new ApiResponse<>(true, "Habitación creada con éxito", savedHabitacionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	/**
	 * Actualizar habitacion.
	 *
	 * @param habitacionDTO the habitacion DTO
	 * @param result the result
	 * @param idHabitacion the id habitacion
	 * @return La habitación actualizada
	 * @throws EntityNotFoundException the entity not found exception
	 * @throws IllegalOperationException the illegal operation exception
	 */
	@PutMapping(value = "/{idHabitacion}", headers = "X-API-VERSION=1.0.0")
    public ResponseEntity<?> actualizarHabitacion(@Valid @RequestBody HabitacionDTO habitacionDTO, BindingResult result, @PathVariable Long idHabitacion) throws EntityNotFoundException, IllegalOperationException {
		if(result.hasErrors()) {
        	return validar(result);
        }
		Habitacion habitacion = modelMapper.map(habitacionDTO, Habitacion.class);
        habiService.actualizarHabitacion(idHabitacion, habitacion);
        HabitacionDTO updatedHabitacionDTO = modelMapper.map(habitacion, HabitacionDTO.class);
        ApiResponse<HabitacionDTO> response = new ApiResponse<>(true, "Habitación actualizada con con éxito",updatedHabitacionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
	
	/**
	 * Valida los errores de entrada y devuelve una respuesta de error con los detalles de los errores.
	 *
	 * @param result El resultado de la validación de entrada.
	 * @return ResponseEntity con los detalles de los errores de validación.
	 */

	private ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

}
