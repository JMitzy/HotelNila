/*
 * @file ComprobanteController.java;
 * @Autor (c)2024 AndersonDietrich
 * @Created 5 mar. 2024,03:32:34
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

import com.grupouno.hotelnila.domain.Comprobante;
import com.grupouno.hotelnila.dto.ClienteDTO;
import com.grupouno.hotelnila.dto.ComprobanteDTO;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.IllegalOperationException;
import com.grupouno.hotelnila.services.ComprobanteService;
import com.grupouno.hotelnila.util.ApiResponse;

import jakarta.validation.Valid;

// TODO: Auto-generated Javadoc
/**
 * Controlador REST para gestionar operaciones relacionadas con los comprobantes.
 */
@RestController
@RequestMapping("/api/comprobantes")
public class ComprobanteController {
	

	@Autowired
	private ComprobanteService comprobanteService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	/**
	 * Listar comprobantes.
	 *
	 * @return ResponseEntity con la lista de comprobantes y un mensaje de exito.
	 */
	@GetMapping
	public ResponseEntity<?> listarComprobantes(){
        List<Comprobante> comprobantes = comprobanteService.listarComprobantes();
        List<ComprobanteDTO> comprobanteDTOs = comprobantes.stream().map(comprobante->modelMapper.map(comprobante, ComprobanteDTO.class))
                .collect(Collectors.toList());
        
        // Crear enlace al recurso de la colección de clientes
        WebMvcLinkBuilder linkToComprobantes = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).listarComprobantes());
        CollectionModel<ComprobanteDTO> comprobanteCollectionModel = CollectionModel.of(comprobanteDTOs);
        comprobanteCollectionModel.add(linkToComprobantes.withSelfRel());
        
        ApiResponse<CollectionModel<ComprobanteDTO>> response = new ApiResponse<>(true, "Lista de comprobantes obtenida con éxito", comprobanteCollectionModel);
        return ResponseEntity.ok(response);
    }
	
	/**
	 * Obtiene un comprobante por su ID.
	 *
	 * @param idComprobante El Id del comprobante que se desea obtener
	 * @return ResponseEntity con el cliente obtenido y un mensaje de exito,
	 *  o una respuesta de error si no se encuentra el comprobante.
	 * @throws EntityNotFoundException Si no se encuentra el comprobante con el ID proporcionado.
	 */
	@GetMapping("/{idComprobante}")
	public ResponseEntity<?> listarPorID(@PathVariable Long idComprobante) throws EntityNotFoundException {
        Comprobante comprobantes = comprobanteService.buscarPorIdComprobante(idComprobante);
        ComprobanteDTO comprobanteDTO = modelMapper.map(comprobantes, ComprobanteDTO.class);
        
     // Crear enlace al recurso cliente
	    EntityModel<ComprobanteDTO> resource = EntityModel.of(comprobanteDTO);
	    WebMvcLinkBuilder linkToCliente = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).listarPorID(idComprobante));
	    resource.add(linkToCliente.withSelfRel());

	    // Agregar mensajes para verificar la generación del enlace
	    if (resource.hasLinks()) {
	        System.out.println("Enlace generado correctamente para el comprobante con ID: " + idComprobante);
	    } else {
	        System.out.println("Error al generar el enlace para el comprobante con ID: " + idComprobante);
	    }

	    return ResponseEntity.ok(resource);
	}
	
	/**
	 * Crear un nuevo comprobante.
	 *
	 * @param comprobanteDTO El DTO del comprobante que se desea obtener.
	 * @param result El resultado de la validación de entrada.
	 * @return ResponseEntity con el comprobante creado y un mensaje de exito, 
	 * o una respuesta de error si hay errores de validacion.
	 * @throws IllegalOperationException Si ocurre un error durante la operacion de creacion del comprobante.
	 */
	@PostMapping
	public ResponseEntity<?> crearComprobante(@Valid @RequestBody ComprobanteDTO comprobanteDTO,BindingResult result ) throws IllegalOperationException {
		if(result.hasErrors()) {
			return validar(result);
		}
        Comprobante comprobante = modelMapper.map(comprobanteDTO, Comprobante.class);
        comprobanteService.crearComprobante(comprobante);
        ComprobanteDTO savedComprobanteDTO = modelMapper.map(comprobante, ComprobanteDTO.class);
        ApiResponse<ComprobanteDTO> response = new ApiResponse<>(true, "Comprobante creada con éxito", savedComprobanteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
	
	/**
	 * Actualiza un comprobante existente.
	 *
	 * @param comprobanteDTO El DTO del comprobante con los nuevos datos
	 * @param result  El resultado de la validación de entrada.
	 * @param idComprobante El id del comprobante que se desea actualizar
	 * @return ResponseEntity con el comprobante actualizado  y un mensaje de exito, o una respuesta de error si hay errores de validacion.
	 * @throws EntityNotFoundException Si no encuentra el comprobante con el Id proporcionado.
	 * @throws IllegalOperationException Si ocurre un error durante la operacion de actualizacion del comprobante.
	 */
	@PutMapping("/{idComprobante}")
	public ResponseEntity<?> actualizarComprobante(@Valid @RequestBody ComprobanteDTO comprobanteDTO,BindingResult result, @PathVariable Long idComprobante) throws EntityNotFoundException, IllegalOperationException {
		if(result.hasErrors()) {
			return validar(result);
		}
		Comprobante comprobante = modelMapper.map(comprobanteDTO, Comprobante.class);
        comprobanteService.actualizarComprobante(idComprobante,comprobante);
        ComprobanteDTO updatedComprobanteDTO = modelMapper.map(comprobante, ComprobanteDTO.class);
        ApiResponse<ComprobanteDTO> response = new ApiResponse<>(true, "Cliente actualizado con éxito",updatedComprobanteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
	
	/**
	 * Eliminar un comprobante existente.
	 *
	 * @param idComprobante el id del comprobante que se desea eliminar.
	 * @return ResponseEntity con un mensaje de exito despues de eliminar el comprobante.
	 * @throws EntityNotFoundException Si no se encuentra el comprobante con el ID proporcionado.
	 * @throws IllegalOperationException Si ocurre un error durante la operacion de eliminacion del comprobante.
	 */
	@DeleteMapping("/{idComprobante}")
	public ResponseEntity<?> eliminarComprobante(@PathVariable Long idComprobante) throws EntityNotFoundException, IllegalOperationException {
		comprobanteService.eliminarComprobante(idComprobante);
        ApiResponse<String> response = new ApiResponse<>(true, "Comprobante eliminada con éxito", null);
        return ResponseEntity.ok(response);
    }
	
	/**
	  * Asigna una reserva a un comprobante.
	  *
	  * @param idComprobante El ID del comprobante al que se desea asignar la reserva.
	  * @param idReserva     El ID de la reserva que se desea asignar al comprobante.
	  * @return ResponseEntity con el comprobante actualizado y un mensaje de exito.
	  * @throws EntityNotFoundException    Si no se encuentra el comprobante o la reserva con los IDs proporcionados.
	  * @throws IllegalOperationException Si ocurre un error durante la operacion de asignacion de la reserva.
	 */
	@PutMapping(value = "/asignarReserva/{idComprobante}/{idReserva}")
    public ResponseEntity<?> asignarReserva (@PathVariable Long idComprobante, @PathVariable Long idReserva) throws EntityNotFoundException, IllegalOperationException {
        Comprobante comprobante = comprobanteService.asignarReserva(idComprobante, idReserva);
        ComprobanteDTO comprobanteDTO = modelMapper.map(comprobante, ComprobanteDTO.class);
        ApiResponse<ComprobanteDTO> response = new ApiResponse<>(true, "Reserva asignada con éxito", comprobanteDTO);
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
