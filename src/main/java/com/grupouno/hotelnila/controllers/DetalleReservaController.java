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
import com.grupouno.hotelnila.dto.DetalleReservaDTO;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.IllegalOperationException;
import com.grupouno.hotelnila.services.DetalleReservaService;
import com.grupouno.hotelnila.util.ApiResponse;

import jakarta.validation.Valid;

// TODO: Auto-generated Javadoc
/**
 * The Class DetalleReservaController.
 */
@RestController
@RequestMapping("/api/detalle-reservas")
public class DetalleReservaController {
    
    /** The det res service. */
    @Autowired
    private DetalleReservaService detResService;

    /** The model mapper. */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Listar detalles reservas.
     *
     * @return the response entity
     */
    @GetMapping
    public ResponseEntity<?> listarDetallesReservas(){
        List<DetalleReserva> detalleReservas = detResService.listarDetallesReservas();
        List<DetalleReservaDTO> detalleReservasDTOs = detalleReservas.stream().map(detallereservas->modelMapper.map(detallereservas, DetalleReservaDTO.class))
                .collect(Collectors.toList());
        ApiResponse<List<DetalleReservaDTO>> response = new ApiResponse<>(true, "Lista de detalles reservas obtenidas con éxito", detalleReservasDTOs);
        return ResponseEntity.ok(response);
    }

    /**
     * Buscar por id detalle reserva.
     *
     * @param idReservaHabitacion the id reserva habitacion
     * @return the response entity
     * @throws EntityNotFoundException the entity not found exception
     */
    @GetMapping("/{idDetalleReserva}")
    public ResponseEntity<?> buscarPorIdDetalleReserva(@PathVariable Long idReservaHabitacion) throws EntityNotFoundException {
        DetalleReserva detalleReservas = detResService.buscarPorIdDetalleReserva(idReservaHabitacion);
        DetalleReservaDTO detalleReservasDTO = modelMapper.map(detalleReservas, DetalleReservaDTO.class);
        ApiResponse<DetalleReservaDTO> response = new ApiResponse<>(true, "Detalles reservas obtenidas con éxito", detalleReservasDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * Crear detalle reserva.
     *
     * @param detalleReservaDTO the detalle reserva DTO
     * @param result the result
     * @return the response entity
     * @throws IllegalOperationException the illegal operation exception
     */
    @PostMapping
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
     * @param result the result
     * @param idReservaHabitacion the id reserva habitacion
     * @return the response entity
     * @throws EntityNotFoundException the entity not found exception
     * @throws IllegalOperationException the illegal operation exception
     */
    @PutMapping("/{idReservaHabitacion}")
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
    @PutMapping(value = "/reserva/{idReservaHabitacion}/{idReserva}")
    public ResponseEntity<?> asignarReserva(@PathVariable Long idReservaHabitacion, @PathVariable Long idReserva) throws EntityNotFoundException, IllegalOperationException {
        DetalleReserva detalleReserva = detResService.asignarReserva(idReservaHabitacion, idReserva);
        DetalleReservaDTO detalleReservaDTO = modelMapper.map(detalleReserva,DetalleReservaDTO.class);
        ApiResponse<DetalleReservaDTO> response = new ApiResponse<>(true, "Detalle reserva asignada con éxito", detalleReservaDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * Asignar habitacion.
     *
     * @param idReservaHabitacion the id reserva habitacion
     * @param idHabitacion the id habitacion
     * @return the response entity
     * @throws EntityNotFoundException the entity not found exception
     * @throws IllegalOperationException the illegal operation exception
     */
    @PutMapping(value = "/habitacion/{idReservaHabitacion}/{idHabitacion}")
    public ResponseEntity<?> asignarHabitacion(@PathVariable Long idReservaHabitacion, @PathVariable Long idHabitacion) throws EntityNotFoundException, IllegalOperationException {
        DetalleReserva detalleReserva = detResService.asignarHabitacion(idReservaHabitacion, idHabitacion);
        DetalleReservaDTO detalleReservaDTO = modelMapper.map(detalleReserva,DetalleReservaDTO.class);
        ApiResponse<DetalleReservaDTO> response = new ApiResponse<>(true, "Detalle reserva asignada con éxito", detalleReservaDTO);
        return ResponseEntity.ok(response);
    }
    
    /**
     * Validar.
     *
     * @param result the result
     * @return the response entity
     */
    private ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}