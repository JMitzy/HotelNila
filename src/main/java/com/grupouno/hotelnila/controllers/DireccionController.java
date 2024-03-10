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
import com.grupouno.hotelnila.domain.Direccion;

import com.grupouno.hotelnila.dto.ClienteDTO;

import com.grupouno.hotelnila.dto.DireccionDTO;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.IllegalOperationException;
import com.grupouno.hotelnila.services.DireccionService;
import com.grupouno.hotelnila.util.ApiResponse;

import jakarta.validation.Valid;


/**
 * Controlador REST para gestionar operaciones relacionadas con las direcciones.
 */
@RestController
@RequestMapping("/api/direcciones")
public class DireccionController {

	@Autowired
    private DireccionService direcService;


    @Autowired
    private ModelMapper modelMapper;

	/**
	* Obtiene una lista de todos las direcciones.
     *
     * @return ResponseEntity con la lista de direcciones y un mensaje de éxito
	 */
	@GetMapping(headers = "X-API-VERSION=1.0.0")
    public ResponseEntity<?> listarDirecciones(){
        List<Direccion> direcciones = direcService.listarDirecciones();
        List<DireccionDTO> direcDTOs = direcciones.stream().map(direccion->modelMapper.map(direccion, DireccionDTO.class))
                .collect(Collectors.toList());

        
     // Crear enlace al recurso de la colección de clientes
        WebMvcLinkBuilder linkToDirecciones = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).listarDirecciones());
        CollectionModel<DireccionDTO> direccionCollectionModel = CollectionModel.of(direcDTOs);
        direccionCollectionModel.add(linkToDirecciones.withSelfRel());
        
        ApiResponse<CollectionModel<DireccionDTO>> response = new ApiResponse<>(true, "Lista de clientes obtenida con éxito",
        		direccionCollectionModel);

        return ResponseEntity.ok(response);
    }
	
	
	/**
	 * Obtiene una dirección por su ID.
	 *
	 * @param idDireccion El id de la direccion que se desea obtener
	 * @return ResponseEntity con la direccion encontrada y un mensaje de éxito, o una respuesta de error si no se encuentra la direccion.
	 * @throws EntityNotFoundException Si no se encuentra la direccion con el ID proporcionado.
	 */
	@GetMapping(value = "/{idDireccion}",  headers = "X-API-VERSION=1.0.0")
    public ResponseEntity<?> listarPorID(@PathVariable Long idDireccion) throws EntityNotFoundException {
        Direccion direcciones = direcService.buscarPorIdDireccion(idDireccion);
        DireccionDTO direccionDTO = modelMapper.map(direcciones, DireccionDTO.class);

     
        // Crear enlace al recurso cliente
	    EntityModel<DireccionDTO> resource = EntityModel.of(direccionDTO);
	    WebMvcLinkBuilder linkToDireccion = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).listarPorID(idDireccion));
	    resource.add(linkToDireccion.withSelfRel());

	    // Agregar mensajes para verificar la generación del enlace
	    if (resource.hasLinks()) {
	        System.out.println("Enlace generado correctamente para el cliente con ID: " + idDireccion);
	    } else {
	        System.out.println("Error al generar el enlace para el cliente con ID: " + idDireccion);
	    }

	    return ResponseEntity.ok(resource);
	}
	
	/**
	 * Crea una nueva dirección.
	 *
	 * @param direccionDTO El DTO de la dirección que se desea crear
	 * @param result El resultado de la validación de entrada
	 * @return ResponseEntity con la dirección creada y un mensaje de éxito
	 * @throws IllegalOperationException the illegal operation exception Si ocurre un error durante la operación de creación de la dirección.
	 */
	@PostMapping(headers = "X-API-VERSION=1.0.0")
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
	 * Actualiza una direccion existente.
	 *
	 * @param direccionDTO El DTO de la direccion con los nuevos datos
	 * @param result the result
	 * @param idDireccion El ID de la direccion que se desea actualizar.
	 * @return ResponseEntity con la direccion actualizada y un mensaje de éxito, 
	 * o una respuesta de error si hay errores de validacion.
	 * @throws EntityNotFoundException Si no se encuentra la direccion con el ID proporcionado.
	 * @throws IllegalOperationException Si ocurre un error durante la operacion de actualizacion de la direccion.
	 */
	@PutMapping(value = "/{idDireccion}", headers = "X-API-VERSION=1.0.0")
    public ResponseEntity<?> actualizarDireccion(@Valid @RequestBody DireccionDTO direccionDTO,BindingResult result, @PathVariable Long idDireccion) throws EntityNotFoundException, IllegalOperationException {
		Direccion direccion = modelMapper.map(direccionDTO, Direccion.class);
        direcService.actualizarDireccion(idDireccion,direccion);
        DireccionDTO updatedDireccionDTO = modelMapper.map(direccion, DireccionDTO.class);
        ApiResponse<DireccionDTO> response = new ApiResponse<>(true, "Direccion actualizada con éxito",updatedDireccionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    } 
	
	/**
	 * Elimina una direccion existente.
	 *
	 * @param idDireccion El id de la direccion que se desea eliminar. 
	 * @return ResponseEntity con un mensaje de exito despues de eliminar la direccion.
	 * @throws EntityNotFoundException si no se encuentra la direccion con el id proporcionado
	 * @throws IllegalOperationException Si ocurre un error durante la operacion de eliminacion de la direccion.
	 */
	@DeleteMapping(value = "/{idDireccion}",  headers = "X-API-VERSION=1.0.0")
    public ResponseEntity<?> eliminarDireccion(@PathVariable Long idDireccion) throws EntityNotFoundException, IllegalOperationException {
		direcService.eliminarDireccion(idDireccion);
        ApiResponse<String> response = new ApiResponse<>(true, "Direccion eliminada con éxito", null);
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


