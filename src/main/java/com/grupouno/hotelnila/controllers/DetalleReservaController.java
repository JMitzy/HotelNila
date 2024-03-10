/*
 * @file DetalleReservaController.java;
 * @Autor (c)2024 JuanRuiz
 * @Created 5 mar. 2024,03:26:13
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

import com.grupouno.hotelnila.domain.DetalleReserva;

import com.grupouno.hotelnila.dto.ClienteDTO;

import com.grupouno.hotelnila.dto.DetalleReservaDTO;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.IllegalOperationException;
import com.grupouno.hotelnila.services.DetalleReservaService;
import com.grupouno.hotelnila.util.ApiResponse;

import jakarta.validation.Valid;

/**
 * Controlador REST para gestionar operaciones relacionadas con los detalles de las reservas.
 */
@RestController
@RequestMapping("/api/detalle-reservas")
public class DetalleReservaController {
    
  
    @Autowired
    private DetalleReservaService detResService;

   
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Listar de detalles de la reserva.
     *
     * @return ResponseEntity con la lista de detalles de reserva y un mensaje de exito.
     */
    @GetMapping(headers = "X-API-VERSION=1.0.0")
    public ResponseEntity<?> listarDetallesReservas(){
        List<DetalleReserva> detalleReservas = detResService.listarDetallesReservas();
        List<DetalleReservaDTO> detalleReservasDTOs = detalleReservas.stream().map(detallereservas->modelMapper.map(detallereservas, DetalleReservaDTO.class))
                .collect(Collectors.toList());

        
     // Crear enlace al recurso de la colección de clientes
        WebMvcLinkBuilder linkToDetalleReservas = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).listarDetallesReservas());
        CollectionModel<DetalleReservaDTO> detResCollectionModel = CollectionModel.of(detalleReservasDTOs);
        detResCollectionModel.add(linkToDetalleReservas.withSelfRel());
        
        ApiResponse<CollectionModel<DetalleReservaDTO>> response = new ApiResponse<>(true, "Lista de detalles reservas obtenidas con éxito", detResCollectionModel);
        return ResponseEntity.ok(response);
    }

    /**
     * Obtiene un detalle de reserva por su id
     *
     * @param idReservaHabitacion El id del detalle de reserva que se desea obtener
     * @return  ResponseEntity con el detalle de reserva obtenido y un mensaje de exito,
     *  o una respuesta de error si no se encuentra el cliente.
     * @throws EntityNotFoundException Si no se encuentra el detalle de reserva con el ID proporcionado.
     */

    @GetMapping(value = "/{idDetalleReserva}",  headers = "X-API-VERSION=1.0.0")
    public ResponseEntity<?> buscarPorIdDetalleReserva(@PathVariable Long idDetalleReserva) throws EntityNotFoundException {
        DetalleReserva detalleReservas = detResService.buscarPorIdDetalleReserva(idDetalleReserva);

        DetalleReservaDTO detalleReservasDTO = modelMapper.map(detalleReservas, DetalleReservaDTO.class);
        
	    // Crear enlace al recurso cliente
	    EntityModel<DetalleReservaDTO> resource = EntityModel.of(detalleReservasDTO);
	    WebMvcLinkBuilder linkToDetalleReserva = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).buscarPorIdDetalleReserva(idDetalleReserva));
	    resource.add(linkToDetalleReserva.withSelfRel());

	    // Agregar mensajes para verificar la generación del enlace
	    if (resource.hasLinks()) {
	        System.out.println("Enlace generado correctamente para el cliente con ID: " + idDetalleReserva);
	    } else {
	        System.out.println("Error al generar el enlace para el cliente con ID: " + idDetalleReserva);
	    }
	    
        return ResponseEntity.ok(resource);

    }

    /**
     * Crea un nuevo detalle de reserva.
     *
     * @param detalleReservaDTO El DTO del detalle de la reserva que se desea crear
     * @param result El resultado de la validación de entrada.
     * @return ResponseEntity con el detalle reserva creado y un mensaje de exito, 
     * o una respuesta de error si hay errores de validacion.
     * @throws IllegalOperationException Si ocurre un error durante la operacion de creacion del detalle de reserva.
     */
    @PostMapping(headers = "X-API-VERSION=1.0.0")
    public ResponseEntity<?> crearDetalleReserva (@Valid @RequestBody DetalleReservaDTO detalleReservaDTO, BindingResult result) throws IllegalOperationException {
    	if(result.hasErrors()) {
			return validar(result);
		}
    	DetalleReserva detalleReserva = modelMapper.map(detalleReservaDTO, DetalleReserva.class);
        detResService.crearDetalleReserva(detalleReserva);
        DetalleReservaDTO savedDetalleReservaDTO = modelMapper.map(detalleReserva, DetalleReservaDTO.class);
        ApiResponse<DetalleReservaDTO> response = new ApiResponse<>(true, "Detalle reserva creada con éxito", savedDetalleReservaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Actualizar detalle reserva.
     *
     * @param detalleReservaDTO the detalle reserva DTO
     * @param result El resultado de la validación de entrada.
     * @param idReservaHabitacion the id reserva habitacion
     * @return the response entity
     * @throws EntityNotFoundException the entity not found exception
     * @throws IllegalOperationException the illegal operation exception
     */
    @PutMapping(value = "/{idReservaHabitacion}",  headers = "X-API-VERSION=1.0.0")
    public ResponseEntity<?>  actualizarDetalleReserva(@Valid @RequestBody DetalleReservaDTO detalleReservaDTO,BindingResult result,@PathVariable Long idReservaHabitacion) throws EntityNotFoundException, IllegalOperationException {
    	if(result.hasErrors()) {
        	return validar(result);
        }
    	DetalleReserva detalleReserva = modelMapper.map(detalleReservaDTO, DetalleReserva.class);
        detResService.actualizarDetalleReserva(idReservaHabitacion, detalleReserva);
        DetalleReservaDTO updatedDetalleReservaDTO = modelMapper.map(detalleReserva, DetalleReservaDTO.class);
        ApiResponse<DetalleReservaDTO> response = new ApiResponse<>(true, "Detalle reserva actualizada con éxito", updatedDetalleReservaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Asignar reserva.
     *
     * @param idReservaHabitacion the id reserva habitacion
     * @param idReserva the id reserva
     * @return the response entity
     * @throws EntityNotFoundException the entity not found exception
     * @throws IllegalOperationException the illegal operation exception
     */

    @PutMapping(value = "/reserva/{idReservaHabitacion}/{idReserva}",  headers = "X-API-VERSION=1.1.0")
    public ResponseEntity<?> asignarReserva(@PathVariable Long idDetalleReserva, @PathVariable Long idReserva) throws EntityNotFoundException, IllegalOperationException {
        DetalleReserva detalleReserva = detResService.asignarReserva(idDetalleReserva, idReserva);

        DetalleReservaDTO detalleReservaDTO = modelMapper.map(detalleReserva,DetalleReservaDTO.class);
        ApiResponse<DetalleReservaDTO> response = new ApiResponse<>(true, "Detalle reserva asignada con éxito", detalleReservaDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * Asigna una habitación a un detalle de reserva.
     *
     * @param idReservaHabitacion El ID del detalle de reserva al que se desea asignar la habitacion.
     * @param idHabitacion        El ID de la habitacion que se desea asignar al detalle de reserva.
     * @return ResponseEntity con el detalle de reserva actualizado y un mensaje de exito.
     * @throws EntityNotFoundException    Si no se encuentra el detalle de reserva o la habitacion con los IDs proporcionados.
     * @throws IllegalOperationException Si ocurre un error durante la operacion de asignacion de la habitacion.
     */

    @PutMapping(value = "/habitacion/{idReservaHabitacion}/{idHabitacion}",  headers = "X-API-VERSION=1.1.0")
    public ResponseEntity<?> asignarHabitacion(@PathVariable Long idReservaHabitacion, @PathVariable Long idHabitacion) throws EntityNotFoundException, IllegalOperationException {
        DetalleReserva detalleReserva = detResService.asignarHabitacion(idReservaHabitacion, idHabitacion);
        DetalleReservaDTO detalleReservaDTO = modelMapper.map(detalleReserva,DetalleReservaDTO.class);
        ApiResponse<DetalleReservaDTO> response = new ApiResponse<>(true, "Detalle reserva asignada con éxito", detalleReservaDTO);
        return ResponseEntity.ok(response);
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