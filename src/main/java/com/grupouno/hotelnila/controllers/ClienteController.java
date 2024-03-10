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
import com.grupouno.hotelnila.dto.ClienteDTO;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.IllegalOperationException;
import com.grupouno.hotelnila.services.ClienteService;
import com.grupouno.hotelnila.util.ApiResponse;

import jakarta.validation.Valid;



/**
 * * La clase ClienteController proporciona endpoints para operaciones relacionadas con clientes.
 */
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	@Autowired
    private ClienteService clienteService;

    /** ModelMapper para mapeo de DTOs. */
    @Autowired
    private ModelMapper modelMapper;

	/**
	 * Obtiene una lista de todos los clientes.
     *
     * @return ResponseEntity con la lista de clientes y un mensaje de éxito
	 * @throws NoHandlerFoundException 
	 */   
    @GetMapping(headers="X-API-VERSION=1.0.0")
    public ResponseEntity<?> listarClientes() throws NoHandlerFoundException {
        List<Cliente> clientes = clienteService.listarClientes();
        if (clientes.isEmpty()) {
            throw new NoHandlerFoundException("GET", "/clientes", null);
        }
        List<ClienteDTO> clienteDTOs = clientes.stream().map(cliente->modelMapper.map(cliente, ClienteDTO.class))
                .collect(Collectors.toList());
        ApiResponse<List<ClienteDTO>> response = new ApiResponse<>(true, "Lista de clientes obtenida con éxito", clienteDTOs);
        return ResponseEntity.ok(response);
    }
	
	
	/**
	 * Obtiene un cliente por su ID.
     *
     * @param ID del cliente a buscar
     * @return ResponseEntity con el cliente encontrado y un mensaje de éxito
     * @throws EntityNotFoundException
	 */
	@GetMapping(value = "/{idCliente}", headers = "X-API-VERSION=1.0.0")
    public ResponseEntity<?> listarPorID(@PathVariable Long idCliente) throws EntityNotFoundException {
        Cliente clientes = clienteService.buscarPorIdCliente(idCliente);
        ClienteDTO clienteDTO = modelMapper.map(clientes, ClienteDTO.class);
        ApiResponse<ClienteDTO> response = new ApiResponse<>(true, "Cliente obtenido con éxito", clienteDTO);
        return ResponseEntity.ok(response);
    }
	
	/**
	 * Crea un nuevo cliente.
     *
     * @param clienteDTO: el DTO del cliente a crear
     * @return ResponseEntity con el cliente creado y un mensaje de éxito
     * @throws IllegalOperationException
	 */
	@PostMapping(headers = "X-API-VERSION=1")
    public ResponseEntity<?> crearCliente(@Valid @RequestBody ClienteDTO clienteDTO, BindingResult result) throws IllegalOperationException {
		if(result.hasErrors()) {
			return validar(result);
		}
		Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
        clienteService.crearCliente(cliente);
        ClienteDTO savedClienteDTO = modelMapper.map(cliente, ClienteDTO.class);
        ApiResponse<ClienteDTO> response = new ApiResponse<>(true, "Cliente creado con éxito", savedClienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
	
	/**
	 * Actualizar cliente.
	 *
	 * @param id del cliente
	 * @param  Información actualizada del cliente
     * @return El cliente actualizado
     * @throws EntityNotFoundException 
     * @throws IllegalOperationException
	 */
	@PutMapping(value = "/{idCliente}", headers = "X-API-VERSION=1.0.0")
    public ResponseEntity<?> actualizarCliente(@Valid @RequestBody ClienteDTO clienteDTO,BindingResult result, @PathVariable Long idCliente) throws EntityNotFoundException, IllegalOperationException {
		if(result.hasErrors()) {
        	return validar(result);
        }
		Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
        clienteService.actualizarCliente(idCliente,cliente);
        ClienteDTO updatedClienteDTO = modelMapper.map(cliente, ClienteDTO.class);
        ApiResponse<ClienteDTO> response = new ApiResponse<>(true, "Cliente actualizado con éxito",updatedClienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    } 
	
	
	/**
	  * Elimina un cliente por su ID.
     *
     * @param  ID del cliente a eliminar
     * @return ResponseEntity con un mensaje de éxito
     * @throws EntityNotFoundException 
     * @throws IllegalOperationException 
	 */
	@DeleteMapping(value = "/{idCliente}", headers = "X-API-VERSION=1.0.0")
    public ResponseEntity<?> eliminarCliente(@PathVariable Long idCliente) throws EntityNotFoundException, IllegalOperationException {
        clienteService.eliminarCliente(idCliente);
        ApiResponse<String> response = new ApiResponse<>(true, "CLiente eliminado con éxito", null);
        return ResponseEntity.ok(response);
    }
	
	/**
	 * Asignar direccion a un cliente.
	 *
	 * @param id  del cliente
	 * @param id de la dirección
	 * @return ResponseEntity con un mensaje de éxito
	 * @throws EntityNotFoundException 
	 * @throws IllegalOperationException 
	 */
	@PutMapping(value = "/asignarDireccion/{idCliente}/{idDireccion}", headers = "X-API-VERSION=1.1.0")
    public ResponseEntity<?> asignarDireccion (@PathVariable Long idCliente, @PathVariable Long idDireccion) throws EntityNotFoundException, IllegalOperationException {
        Cliente cliente = clienteService.asignarDireccion(idCliente, idDireccion);
        ClienteDTO clienteDTO = modelMapper.map(cliente, ClienteDTO.class);
        ApiResponse<ClienteDTO> response = new ApiResponse<>(true, "Dirección asignada con éxito", clienteDTO);
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
