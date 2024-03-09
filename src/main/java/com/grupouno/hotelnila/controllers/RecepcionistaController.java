/*
 * @file RecepcionistaController.java;
 * @Autor (c)2024 ErickBecerra
 * @Created 5 mar. 2024,00:10:13
 */

package com.grupouno.hotelnila.controllers;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.grupouno.hotelnila.domain.Recepcionista;
import com.grupouno.hotelnila.domain.Reserva;
import com.grupouno.hotelnila.dto.RecepcionistaDTO;
import com.grupouno.hotelnila.dto.ReservaDTO;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.IllegalOperationException;
import com.grupouno.hotelnila.services.RecepcionistaService;
import com.grupouno.hotelnila.util.ApiResponse;

import jakarta.validation.Valid;


/**
 * Controlador REST para gestionar operaciones relacionadas con los recepcionistas.
 */
@RestController
@RequestMapping("/api/recepcionistas")
public class RecepcionistaController {
	
	
	@Autowired
	private RecepcionistaService recepcionistaService;

	
	@Autowired
	private ModelMapper modelMapper;

    /**
     * Obtiene una lista de todos los recepcionistas.
     *
     * @return ResponseEntity con la lista de recepcionistas y un mensaje de éxito
     */
    @GetMapping
    public ResponseEntity<?> listarRecepcionistas() {
        List<Recepcionista> recepcionistas = recepcionistaService.listarRecepcionistas();
        List<RecepcionistaDTO> recepcionistaDTOs = recepcionistas.stream().map(recepcionista -> modelMapper.map(recepcionista, RecepcionistaDTO.class))
                .collect(Collectors.toList());
        ApiResponse<List<RecepcionistaDTO>> response = new ApiResponse<>(true, "Lista de recepcionistas obtenida con éxito", recepcionistaDTOs);
        return ResponseEntity.ok(response);
    }

    /**
     * Obtiene un recepcionista por su ID.
     *
     * @param idRecepcionista el ID del recepcionista a buscar
     * @return ResponseEntity con el recepcionista encontrado y un mensaje de exito
     * @throws EntityNotFoundException si no se encuentra la entidad
     */
    @GetMapping("/{idRecepcionista}")
    public ResponseEntity<?> listarPorID(@PathVariable Long idRecepcionista) throws EntityNotFoundException {
        Recepcionista recepcionista = recepcionistaService.buscarPorIdRecepcionista(idRecepcionista);
        RecepcionistaDTO recepcionistaDTO = modelMapper.map(recepcionista, RecepcionistaDTO.class);
        ApiResponse<RecepcionistaDTO> response = new ApiResponse<>(true, "Recepcionista obtenido con éxito", recepcionistaDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * Crea un nuevo recepcionista.
     *
     * @param recepcionistaDTO el DTO del recepcionista a crear
     * @param result El resultado de la validación de entrada.
     * @return ResponseEntity con el recepcionista creado y un mensaje de exito,
     * o una respuesta de error si hay errores de validacion.
     * @throws IllegalOperationException si la operación es ilegal
     */
    @PostMapping
    public ResponseEntity<?> crearRecepcionista(@Valid @RequestBody RecepcionistaDTO recepcionistaDTO,  BindingResult result) throws IllegalOperationException {
        
    	if(result.hasErrors()) {
			return validar(result);
		}
    	
    	Recepcionista recepcionista = modelMapper.map(recepcionistaDTO, Recepcionista.class);
        recepcionistaService.crearRecepcionista(recepcionista);
        RecepcionistaDTO savedRecepcionistaDTO = modelMapper.map(recepcionista, RecepcionistaDTO.class);
        ApiResponse<RecepcionistaDTO> response = new ApiResponse<>(true, "Recepcionista creado con éxito", savedRecepcionistaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    /**
     * Actualizar recepcionista.
     *
     * @param recepcionistaDTO el DTO del recepcionista
     * @param result el resultado
     * @param idRecepcionista el ID del recepcionista
     * @return ResponseEntity con el recepcionista actualizado y un mensaje de éxito,
     * o una respuesta de error si hay errores de validacion.
     * @throws IllegalOperationException  Si ocurre un error durante la operación de actualizacion del recepcionista.
     * @throws EntityNotFoundException  Si no se encuentra el recepcionista con el ID proporcionado.
     */
    @PutMapping("/{idRecepcionista}")
    public ResponseEntity<?> actualizarRecepcionista(@Valid @RequestBody RecepcionistaDTO recepcionistaDTO, BindingResult result, @PathVariable Long idRecepcionista) throws IllegalOperationException, EntityNotFoundException {
        
        if(result.hasErrors()) {
            return validar(result);
        }
        
        // Validar lógica de negocio

        /*if (recepcionistaDTO.getApePat() == null || recepcionistaDTO.getApePat().isEmpty()) {

        if (recepcionistaDTO.getApePat() == null || recepcionistaDTO.getApePat().isEmpty()) {

            return ResponseEntity.badRequest().body(Collections.singletonMap("mensaje", "El campo 'apePat' no puede estar vacío"));
        }
        
        if (recepcionistaDTO.getApeMat() == null || recepcionistaDTO.getApeMat().isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("mensaje", "El campo 'apeMat' no puede estar vacío"));
        }
        
        if (recepcionistaDTO.getNombre() == null || recepcionistaDTO.getNombre().isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("mensaje", "El campo 'nombre' no puede estar vacío"));
        }
        
        if (recepcionistaDTO.getTelefono() == null || recepcionistaDTO.getTelefono().isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("mensaje", "El campo 'telefono' no puede estar vacío"));
        }
        
        if (!recepcionistaDTO.getTurno().equals("Mañana") && !recepcionistaDTO.getTurno().equals("Tarde")) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("mensaje", "El campo 'turno' debe ser 'Mañana' o 'Tarde'"));
        }*/

        
        
        Recepcionista recepcionista = modelMapper.map(recepcionistaDTO, Recepcionista.class);
        recepcionistaService.actualizarRecepcionista(idRecepcionista, recepcionista);
        RecepcionistaDTO updatedRecepcionistaDTO = modelMapper.map(recepcionista, RecepcionistaDTO.class);
        ApiResponse<RecepcionistaDTO> response = new ApiResponse<>(true, "Recepcionista actualizado con éxito", updatedRecepcionistaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    /**
     * Elimina un recepcionista por su ID.
     *
     * @param idRecepcionista el ID del recepcionista a eliminar
     * @return ResponseEntity con un mensaje de exito
     * @throws EntityNotFoundException Si no se encuentra el recepcionista con el ID proporcionado.
     * @throws IllegalOperationException Si ocurre un error durante la operacion de eliminacion del recepcionista.
     */
    @DeleteMapping("/{idRecepcionista}")
    public ResponseEntity<?> eliminarRecepcionista(@PathVariable Long idRecepcionista) throws EntityNotFoundException, IllegalOperationException {
        recepcionistaService.eliminarRecepcionista(idRecepcionista);
        ApiResponse<String> response = new ApiResponse<>(true, "Recepcionista eliminado con éxito", null);
        return ResponseEntity.ok(response);
    }

    /**
     * Asigna reserva a un recepcionista.
     *
     * @param idRecepcionista el ID del recepcionista
     * @param idReserva el ID de la reserva
     * @return ResponseEntity con un mensaje de éxito
     * @throws EntityNotFoundException si no se encuentra la entidad
     * @throws IllegalOperationException si la operación es ilegal
     */
    @PutMapping("asignarReserva/{idRecepcionista}/{idReserva}")
    public ResponseEntity<?> asignarReserva(@PathVariable Long idRecepcionista, @PathVariable Long idReserva) throws EntityNotFoundException, IllegalOperationException {
        Recepcionista recepcionista = recepcionistaService.asignarReserva(idRecepcionista, idReserva);
        RecepcionistaDTO recepcionistaDTO = modelMapper.map(recepcionista, RecepcionistaDTO.class);
        ApiResponse<RecepcionistaDTO> response = new ApiResponse<>(true, "Reserva asignada con éxito", recepcionistaDTO);
        return ResponseEntity.ok(response);
    }
    
    /**
     * Obtiene reservas de un recepcionista.
     *
     * @param idRecepcionista el ID del recepcionista
     * @return la entidad de respuesta
     * @throws EntityNotFoundException si no se encuentra la entidad
     */
    @GetMapping("/{idRecepcionista}/reservas")
    public ResponseEntity<?> obtenerReservas(@PathVariable Long idRecepcionista) throws EntityNotFoundException {
        List<Reserva> reservas = recepcionistaService.obtenerReservas(idRecepcionista);
        List<ReservaDTO> reservasDTO = reservas.stream()
            .map(reserva -> modelMapper.map(reserva, ReservaDTO.class))
            .collect(Collectors.toList());
        ApiResponse<List<ReservaDTO>> response = new ApiResponse<>(true, "Reservas obtenidas con éxito", reservasDTO);
        return ResponseEntity.ok(response);
    }
    
    /**
     * Obtiene una reserva por su ID para un recepcionista específico.
     *
     * @param idRecepcionista El ID del recepcionista.
     * @param idReserva       El ID de la reserva que se desea obtener.
     * @return ResponseEntity con la reserva obtenida y un mensaje de éxito.
     * @throws EntityNotFoundException    Si no se encuentra el recepcionista o la reserva con los IDs proporcionados.
     * @throws IllegalOperationException Si ocurre un error durante la operación de obtención de la reserva.
     */
    @GetMapping("/{idRecepcionista}/reservas/{idReserva}")
    public ResponseEntity<?> obtenerReservasPorId(@PathVariable Long idRecepcionista,@PathVariable Long idReserva) 
    		throws EntityNotFoundException, IllegalOperationException {
    	Reserva reserva = recepcionistaService.obtenerReservaPorId(idRecepcionista, idReserva);
		ReservaDTO reservaDTO = modelMapper.map(reserva, ReservaDTO.class);
		ApiResponse<ReservaDTO> response = new ApiResponse<>(true, "Reserva obtenida con éxito", reservaDTO);
		return ResponseEntity.ok(response);
    }

    /**
     * Options recepcionistas.
     *
     * @return la entidad de respuesta
     */
    @RequestMapping(path = "/recepcionistas", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> optionsRecepcionistas() {
        return ResponseEntity
                .ok()
                .allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.HEAD, HttpMethod.OPTIONS, HttpMethod.PUT, HttpMethod.PATCH, HttpMethod.DELETE)
                .build();
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