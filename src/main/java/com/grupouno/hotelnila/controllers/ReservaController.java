/*
 * @file ReservaController.java;
 * @Autor (c)2024 JhenniferMendoza
 * @Created 4 mar. 2024,00:27:06
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
import com.grupouno.hotelnila.domain.Reserva;
import com.grupouno.hotelnila.dto.ClienteDTO;
import com.grupouno.hotelnila.dto.HabitacionDTO;
import com.grupouno.hotelnila.dto.ReservaDTO;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.IllegalOperationException;
import com.grupouno.hotelnila.services.ReservaService;
import com.grupouno.hotelnila.util.ApiResponse;

import jakarta.validation.Valid;


/**
 * Controlador REST para gestionar operaciones relacionadas con las reservas.
 */
@RestController
@RequestMapping("/api/reservas")
public class ReservaController {
	
	@Autowired
    private ReservaService resService;

    @Autowired
    private ModelMapper modelMapper;

	/**
	 * Obtiene una lista de todas las reservas.
     *
     * @return ResponseEntity con la lista de reservas y un mensaje de exito
	 */   
	@GetMapping
    public ResponseEntity<?> listarReservas(){
        List<Reserva> reservas = resService.listarReservas();
        List<ReservaDTO> reservaDTOs = reservas.stream().map(reserva->modelMapper.map(reserva, ReservaDTO.class))
                .collect(Collectors.toList());
        
     // Crear enlace al recurso de la colección de clientes
        WebMvcLinkBuilder linkToReservas = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).listarReservas());
        CollectionModel<ReservaDTO> reservaCollectionModel = CollectionModel.of(reservaDTOs);
        reservaCollectionModel.add(linkToReservas.withSelfRel());

        ApiResponse<CollectionModel<ReservaDTO>> response = new ApiResponse<>(true, "Lista de clientes obtenida con éxito",
        		reservaCollectionModel);
        return ResponseEntity.ok(response);
	}
	
	
	/**
	* Obtiene una reserva por su ID.
	*
	* @param idReserva El ID de la reserva que se desea obtener.
	* @return ResponseEntity con la reserva obtenida y un mensaje de exito.
	* @throws EntityNotFoundException Si no se encuentra la reserva con el ID proporcionado.
	*/
	@GetMapping("/{idReserva}")
    public ResponseEntity<?> listarPorID(@PathVariable Long idReserva) throws EntityNotFoundException {
		Reserva reservas = resService.buscarPorIdReserva(idReserva);
		ReservaDTO reservaDTO = modelMapper.map(reservas, ReservaDTO.class);
        
		// Crear enlace al recurso cliente
	    EntityModel<ReservaDTO> resource = EntityModel.of(reservaDTO);
	    WebMvcLinkBuilder linkToReserva = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).listarPorID(idReserva));
	    resource.add(linkToReserva.withSelfRel());

	    // Agregar mensajes para verificar la generación del enlace
	    if (resource.hasLinks()) {
	        System.out.println("Enlace generado correctamente para el cliente con ID: " + idReserva);
	    } else {
	        System.out.println("Error al generar el enlace para el cliente con ID: " + idReserva);
	    }

	    return ResponseEntity.ok(resource);
	}
	
	/**
	 * Crea una nueva reserva.
	 *
	 * @param reservaDTO El objeto ReservaDTO que contiene los datos de la reserva a crear.
	 * @param result     Resultado de la validación de los datos de entrada.
	 * @return ResponseEntity con la reserva creada y un mensaje de éxito, o los errores de validación si los hay.
	 * @throws IllegalOperationException Si ocurre un error durante la operacion de creacion de la reserva.
	 */
	@PostMapping

    public ResponseEntity<?> crearReserva(@Valid @RequestBody ReservaDTO reservaDTO, BindingResult result) throws IllegalOperationException {

		if(result.hasErrors()) {
			return validar(result);
		}
		Reserva reserva = modelMapper.map(reservaDTO, Reserva.class);
        resService.crearReserva(reserva);
        ReservaDTO savedReservaDTO = modelMapper.map(reserva, ReservaDTO.class);
        ApiResponse<ReservaDTO> response = new ApiResponse<>(true, "Reserva creada con éxito", savedReservaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
	
	/**
	 * Actualiza una reserva existente.
	 *
	* @param reservaDTO El objeto ReservaDTO que contiene los datos actualizados de la reserva.
	* @param result     Resultado de la validación de los datos de entrada.
	* @param idReserva  El ID de la reserva que se desea actualizar.
	* @return ResponseEntity con la reserva actualizada y un mensaje de exito, 
	*  o una respuesta de error si hay errores de validacion.
	* @throws EntityNotFoundException    Si no se encuentra la reserva con el ID proporcionado.
	* @throws IllegalOperationException Si ocurre un error durante la operacion de actualizacion de la reserva.
	 */
	@PutMapping("/{idReserva}")
    public ResponseEntity<?> actualizarReserva(@Valid @RequestBody ReservaDTO reservaDTO,BindingResult result, @PathVariable Long idReserva) throws EntityNotFoundException, IllegalOperationException {
		if(result.hasErrors()) {
        	return validar(result);
        }
		Reserva reserva = modelMapper.map(reservaDTO, Reserva.class);
        resService.actualizarReserva(idReserva,reserva);
        ReservaDTO updatedReservaDTO = modelMapper.map(reserva, ReservaDTO.class);
        ApiResponse<ReservaDTO> response = new ApiResponse<>(true, "Reserva actualizada con éxito",updatedReservaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    } 
	
	/**
	 * Asigna un cliente a una reserva existente.
	 *
	 * @param idReserva El ID de la reserva a la que se desea asignar el cliente.
	 * @param idCliente El ID del cliente que se desea asignar a la reserva.
	 * @return ResponseEntity con la reserva actualizada y un mensaje de exito.
	 * @throws EntityNotFoundException    Si no se encuentra la reserva o el cliente con los IDs proporcionados.
	 * @throws IllegalOperationException Si ocurre un error durante la operación de asignación del cliente.
	 */

	@PutMapping("/asignarCliente/{idReserva}/{idCliente}")
    public ResponseEntity<?> asignarCliente (@PathVariable Long idReserva, @PathVariable Long idCliente) throws EntityNotFoundException, IllegalOperationException {
        Reserva reserva = resService.asignarCliente(idReserva, idCliente);
        ReservaDTO reservaDTO = modelMapper.map(reserva, ReservaDTO.class);
        ApiResponse<ReservaDTO> response = new ApiResponse<>(true, "Cliente asignado con éxito", reservaDTO);
        return ResponseEntity.ok(response);
    }
	 
 	/**
 	 * Obtiene las habitaciones asociadas a una reserva.
 	 *
 	 * @param idReserva El ID de la reserva de la que se desean obtener las habitaciones.
 	 * @return ResponseEntity con la lista de habitaciones asociadas a la reserva y un mensaje de exito.
	* @throws EntityNotFoundException    Si no se encuentra la reserva con el ID proporcionado.
	* @throws IllegalOperationException Si ocurre un error durante la operacion de obtencion de las habitaciones.
 	 */
 	@GetMapping("/{idReserva}/habitaciones")
	    public ResponseEntity<?> obtenerHabitaciones(@PathVariable Long idReserva) throws EntityNotFoundException, IllegalOperationException  {
	        List<Habitacion> habitaciones = resService.obtenerHabitaciones(idReserva);
	        List<HabitacionDTO> habitacionDTO = habitaciones.stream()
	            .map(habitacion -> modelMapper.map(habitacion, HabitacionDTO.class))
	            .collect(Collectors.toList());
	        ApiResponse<List<HabitacionDTO>> response = new ApiResponse<>(true, "Habitaciones obtenidas con éxito", habitacionDTO);
	        return ResponseEntity.ok(response);
	    }
	 
 	/**
 	 * Obtiene una habitación por su ID en una reserva.
 	 *
 	 * @param idReserva    El ID de la reserva a la que pertenece la habitación.
 	 * @param idHabitacion El ID de la habitación que se desea obtener.
 	 * @return ResponseEntity con la habitación obtenida y un mensaje de exito.
 	 * @throws EntityNotFoundException    Si no se encuentra la reserva o la habitacion con los IDs proporcionados.
 	 * @throws IllegalOperationException Si ocurre un error durante la operacion de obtencion de la habitacion.
 	 */
 	@GetMapping("/{idReserva}/habitaciones/{idHabitacion}")
	    public ResponseEntity<?> obtenerReservasPorId(@PathVariable Long idReserva,@PathVariable Long idHabitacion) 
	    		throws EntityNotFoundException, IllegalOperationException {
		 Habitacion habitacion = resService.obtenerHabitacionPorId(idReserva, idHabitacion);
		 HabitacionDTO habitacionDTO = modelMapper.map(habitacion, HabitacionDTO.class);
			ApiResponse< HabitacionDTO> response = new ApiResponse<>(true, "Habitacion obtenida con éxito", habitacionDTO);
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

