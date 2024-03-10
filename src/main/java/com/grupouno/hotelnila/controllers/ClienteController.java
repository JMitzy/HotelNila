/*
 * @file ClienteController.java;
 * @Autor (c)2024 JhenniferMendoza
 * @Created 3 mar. 2024,11:55:03
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.NoHandlerFoundException;


import com.grupouno.hotelnila.domain.Cliente;
import com.grupouno.hotelnila.domain.Habitacion;
import com.grupouno.hotelnila.domain.Reserva;
import com.grupouno.hotelnila.dto.ClienteDTO;
import com.grupouno.hotelnila.dto.HabitacionDTO;
import com.grupouno.hotelnila.dto.ReservaDTO;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.IllegalOperationException;
import com.grupouno.hotelnila.services.ClienteService;
import com.grupouno.hotelnila.util.ApiResponse;

import jakarta.validation.Valid;


/**
 * Controlador REST para gestionar operaciones relacionadas con los clientes.
 */
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ModelMapper modelMapper;

	
	/**
	 * Obtiene una lista de todos los clientes.
     *

     * @return ResponseEntity con la lista de clientes y un mensaje de éxito
	 * @throws NoHandlerFoundException 
	 */   

	@GetMapping(headers="X-API-VERSION=1.0.0")
	public ResponseEntity<?> listarClientes() {

        List<Cliente> clientes = clienteService.listarClientes();
        List<ClienteDTO> clienteDTOs = clientes.stream().map(cliente -> modelMapper.map(cliente, ClienteDTO.class))

                .collect(Collectors.toList());

        // Crear enlace al recurso de la colección de clientes
        WebMvcLinkBuilder linkToClientes = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).listarClientes());
        CollectionModel<ClienteDTO> clienteCollectionModel = CollectionModel.of(clienteDTOs);
        clienteCollectionModel.add(linkToClientes.withSelfRel());

        ApiResponse<CollectionModel<ClienteDTO>> response = new ApiResponse<>(true, "Lista de clientes obtenida con éxito",
                clienteCollectionModel);
        return ResponseEntity.ok(response);

	}

	/**
	 * Obtiene un cliente por su id .
	 *
	 * @param idCliente El ID del cliente que se desea obtener.
	 * @return ResponseEntity con el cliente obtenido y un mensaje de exito, o una respuesta de error si no se encuentra el cliente.
	 * @throws EntityNotFoundException Si no se encuentra el cliente con el ID proporcionado.
	 */	

	@GetMapping(value = "/{idCliente}", headers = "X-API-VERSION=1.0.0")
	public ResponseEntity<?> listarPorID(@PathVariable Long idCliente) throws EntityNotFoundException {

	    Cliente cliente = clienteService.buscarPorIdCliente(idCliente);
	    ClienteDTO clienteDTO = modelMapper.map(cliente, ClienteDTO.class);
	    
	    // Crear enlace al recurso cliente
	    EntityModel<ClienteDTO> resource = EntityModel.of(clienteDTO);
	    WebMvcLinkBuilder linkToCliente = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).listarPorID(idCliente));
	    resource.add(linkToCliente.withSelfRel());

	    // Agregar mensajes para verificar la generación del enlace
	    if (resource.hasLinks()) {
	        System.out.println("Enlace generado correctamente para el cliente con ID: " + idCliente);
	    } else {
	        System.out.println("Error al generar el enlace para el cliente con ID: " + idCliente);
	    }

	    return ResponseEntity.ok(resource);
	}

	/**
	 * Crea un nuevo cliente.
	 *
	  * @param clienteDTO El DTO del cliente que se desea crear.
	  * @param result El resultado de la validación de entrada.
	  * @return ResponseEntity con el cliente creado y un mensaje de exito, o una respuesta de error si hay errores de validacion.
	  * @throws IllegalOperationException Si ocurre un error durante la operacion de creacion del cliente.
	 */

	@PostMapping(headers="X-API-VERSION=1.0.0")
	public ResponseEntity<?> crearCliente(@Valid @RequestBody ClienteDTO clienteDTO, BindingResult result)
			throws IllegalOperationException {
		if (result.hasErrors()) {

			return validar(result);
		}
		Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
		clienteService.crearCliente(cliente);
		ClienteDTO savedClienteDTO = modelMapper.map(cliente, ClienteDTO.class);
		ApiResponse<ClienteDTO> response = new ApiResponse<>(true, "Cliente creado con éxito", savedClienteDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	
	/**
	 * Actualiza un cliente existente.
	 *
	 * @param clienteDTO El DTO del cliente con los nuevos datos.
	 * @param result     El resultado de la validación de entrada.
	 * @param idCliente  El ID del cliente que se desea actualizar.
	 * @return ResponseEntity con el cliente actualizado y un mensaje de exito, o una respuesta de error si hay errores de validacion.
	 * @throws EntityNotFoundException    Si no se encuentra el cliente con el ID proporcionado.
	 * @throws IllegalOperationException Si ocurre un error durante la operación de actualizacion del cliente.
	 */

	@PutMapping(value = "/{idCliente}", headers = "X-API-VERSION=1.0.0")
    public ResponseEntity<?> actualizarCliente(@Valid @RequestBody ClienteDTO clienteDTO,BindingResult result, @PathVariable Long idCliente) throws EntityNotFoundException, IllegalOperationException {
		if(result.hasErrors()) {
        	return validar(result);
        }
		Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
		clienteService.actualizarCliente(idCliente, cliente);
		ClienteDTO updatedClienteDTO = modelMapper.map(cliente, ClienteDTO.class);
		ApiResponse<ClienteDTO> response = new ApiResponse<>(true, "Cliente actualizado con éxito", updatedClienteDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	
	/**
	 * Elimina un cliente existente.
	 *
	 * @param idCliente El ID del cliente que se desea eliminar.
	 * @return ResponseEntity con un mensaje de exito despues de eliminar el cliente.
	 * @throws EntityNotFoundException    Si no se encuentra el cliente con el ID proporcionado.
	 * @throws IllegalOperationException Si ocurre un error durante la operacion de eliminacion del cliente.

	 */

	@DeleteMapping(value = "/{idCliente}", headers = "X-API-VERSION=1.0.0")
    public ResponseEntity<?> eliminarCliente(@PathVariable Long idCliente) throws EntityNotFoundException, IllegalOperationException {
        clienteService.eliminarCliente(idCliente);
        ApiResponse<String> response = new ApiResponse<>(true, "CLiente eliminado con éxito", null);
        return ResponseEntity.ok(response);
    }

	
	/**
	 * Asigna una dirección a un cliente.
	 *
	 * @param idCliente  El ID del cliente al que se desea asignar la direccion.
	 * @param idDireccion El ID de la dirección que se desea asignar al cliente.
	 * @return ResponseEntity con el cliente actualizado después de asignar la dirección y un mensaje de éxito.
	 * @throws EntityNotFoundException    Si no se encuentra el cliente o la dirección con los IDs proporcionados.
	 * @throws IllegalOperationException Si ocurre un error durante la operación de asignación de la dirección.
	 */

	@PutMapping(value = "/asignarDireccion/{idCliente}/{idDireccion}", headers = "X-API-VERSION=1.1.0")
    public ResponseEntity<?> asignarDireccion (@PathVariable Long idCliente, @PathVariable Long idDireccion) throws EntityNotFoundException, IllegalOperationException {
        Cliente cliente = clienteService.asignarDireccion(idCliente, idDireccion);
        ClienteDTO clienteDTO = modelMapper.map(cliente, ClienteDTO.class);
        ApiResponse<ClienteDTO> response = new ApiResponse<>(true, "Dirección asignada con éxito", clienteDTO);
        return ResponseEntity.ok(response);
    }
	


	/**
	 * Obtiene todas las reservas asociadas a un cliente.
	 *
	 * @param idCliente El ID del cliente del que se desean obtener las reservas.
	 * @return ResponseEntity con la lista de reservas obtenidas y un mensaje de éxito.
	 * @throws EntityNotFoundException Si no se encuentra el cliente con el ID proporcionado.
	 */
	@GetMapping(value="/{idCliente}/reservas",headers = "X-API-VERSION=1.1.0")
	public ResponseEntity<?> obtenerReservas(@PathVariable Long idCliente) throws EntityNotFoundException {
		List<Reserva> reservas = clienteService.obtenerReservas(idCliente);
		List<ReservaDTO> reservasDTO = reservas.stream().map(reserva -> modelMapper.map(reserva, ReservaDTO.class))
				.collect(Collectors.toList());
		ApiResponse<List<ReservaDTO>> response = new ApiResponse<>(true, "Reservas obtenidas con éxito", reservasDTO);
		return ResponseEntity.ok(response);
	}

	/**
	 * Obtiene una reserva específica de la lista de reservas de un cliente.
	 *
	 * @param idCliente  El ID del cliente del que se desea obtener la reserva.
	 * @param idReserva  El ID de la reserva que se desea obtener.
	 * @return ResponseEntity con la reserva obtenida y un mensaje de éxito.
	 * @throws EntityNotFoundException    Si no se encuentra el cliente o la reserva con los IDs proporcionados.
	 * @throws IllegalOperationException Si ocurre un error durante la operación de obtención de la reserva.
	 */
	@GetMapping(value="/{idCliente}/reservas/{idReserva}", headers = "X-API-VERSION=1.1.0")
	public ResponseEntity<?> obtenerReservaDeLaLista(@PathVariable Long idCliente, @PathVariable Long idReserva)
			throws EntityNotFoundException, IllegalOperationException {
		Reserva reserva = clienteService.obtenerReservaPorId(idCliente, idReserva);
		ReservaDTO reservaDTO = modelMapper.map(reserva, ReservaDTO.class);
		ApiResponse<ReservaDTO> response = new ApiResponse<>(true, "Reserva obtenida con éxito", reservaDTO);
		return ResponseEntity.ok(response);
	}

	/**
	* Obtiene las habitaciones asociadas a una reserva específica de un cliente.
	*
	* @param idCliente  El ID del cliente del que se desea obtener las reservas.
	* @param idReserva  El ID de la reserva de la que se desean obtener las habitaciones.
	* @return ResponseEntity con la lista de habitaciones obtenidas y un mensaje de éxito.
	* @throws EntityNotFoundException    Si no se encuentra el cliente o la reserva con los IDs proporcionados.
	* @throws IllegalOperationException Si ocurre un error durante la operación de obtención de las habitaciones.
	 */
	@GetMapping(value="/{idCliente}/reservas/{idReserva}/habitaciones",headers = "X-API-VERSION=1.1.0")
	public ResponseEntity<?> obtenerHabitaciones(@PathVariable Long idCliente, @PathVariable Long idReserva)
			throws EntityNotFoundException, IllegalOperationException {
		List<Habitacion> habitaciones = clienteService.obtenerHabitaciones(idCliente, idReserva);
		List<HabitacionDTO> habitacionesDTO = habitaciones.stream()
				.map(habitacion -> modelMapper.map(habitacion, HabitacionDTO.class)).collect(Collectors.toList());
		ApiResponse<List<HabitacionDTO>> response = new ApiResponse<>(true, "Habitaciones obtenidas con éxito",
				habitacionesDTO);
		return ResponseEntity.ok(response);
	}

	/**
	* Obtiene una habitación específica de una reserva de un cliente.
	*
	* @param idCliente    El ID del cliente del que se desea obtener la habitación.
	* @param idReserva    El ID de la reserva de la que se desea obtener la habitación.
	* @param idHabitacion El ID de la habitación que se desea obtener.
	* @return ResponseEntity con la habitación obtenida y un mensaje de éxito.
 	* @throws EntityNotFoundException    Si no se encuentra el cliente, la reserva o la habitación con los IDs proporcionados.
 	* @throws IllegalOperationException Si ocurre un error durante la operación de obtención de la habitación.
	 */
	@GetMapping(value="/{idCliente}/reservas/{idReserva}/habitaciones/{idHabitacion}", headers = "X-API-VERSION=1.1.0")
	public ResponseEntity<?> obtenerHabitaciones(@PathVariable Long idCliente, @PathVariable Long idReserva,
			@PathVariable Long idHabitacion) throws EntityNotFoundException, IllegalOperationException {
		Habitacion habitacion = clienteService.obtenerHabitacionPorId(idCliente, idReserva, idHabitacion);
		HabitacionDTO habitacionDTO = modelMapper.map(habitacion, HabitacionDTO.class);
		ApiResponse<HabitacionDTO> response = new ApiResponse<>(true, "Habitacion obtenida con éxito", habitacionDTO);
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
