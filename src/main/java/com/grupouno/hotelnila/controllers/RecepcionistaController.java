/*
 * @file RecepcionistaController.java;
 * @Autor (c)2024 JhenniferMendoza
 * @Created 4 mar. 2024,01:06:06
 */
package com.grupouno.hotelnila.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupouno.hotelnila.domain.Cliente;
import com.grupouno.hotelnila.domain.Recepcionista;
import com.grupouno.hotelnila.dto.ClienteDTO;
import com.grupouno.hotelnila.dto.RecepcionistaDTO;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.IllegalOperationException;
import com.grupouno.hotelnila.services.RecepcionistaService;
import com.grupouno.hotelnila.util.ApiResponse;

/**
 * La clase RecepcionistaController proporciona endpoints para operaciones relacionadas con recepcionistas.
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
     * @return ResponseEntity con el recepcionista encontrado y un mensaje de éxito
     * @throws EntityNotFoundException
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
     * @return ResponseEntity con el recepcionista creado y un mensaje de éxito
     * @throws IllegalOperationException
     */
    @PostMapping
    public ResponseEntity<?> crearRecepcionista(@RequestBody RecepcionistaDTO recepcionistaDTO) throws IllegalOperationException {
        Recepcionista recepcionista = modelMapper.map(recepcionistaDTO, Recepcionista.class);
        recepcionistaService.crearRecepcionista(recepcionista);
        RecepcionistaDTO savedRecepcionistaDTO = modelMapper.map(recepcionista, RecepcionistaDTO.class);
        ApiResponse<RecepcionistaDTO> response = new ApiResponse<>(true, "Recepcionista creado con éxito", savedRecepcionistaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    /**
     * Actualizar recepcionista.
     *
     * @param id del recepcionista
     * @param Dto del recepcionista
     * @return ResponseEntity con el recepcionista creado y un mensaje de éxito
     * @throws EntityNotFoundException 
     * @throws IllegalOperationException 
     */
    @PutMapping("/{idRecepcionista}")
    public ResponseEntity<?> actualizarRecepcionista(@PathVariable Long idRecepcionista, @RequestBody RecepcionistaDTO recepcionistaDTO) throws EntityNotFoundException, IllegalOperationException {
    	Recepcionista recepcionista = modelMapper.map(recepcionistaDTO, Recepcionista.class);
        recepcionistaService.actualizarRecepcionista(idRecepcionista,recepcionista);
        RecepcionistaDTO updatedRecepcionistaDTO = modelMapper.map(recepcionista, RecepcionistaDTO.class);
        ApiResponse<RecepcionistaDTO> response = new ApiResponse<>(true, "Recepcionista actualizado con éxito",updatedRecepcionistaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    } 

    /**
     * Elimina un recepcionista por su ID.
     *
     * @param idRecepcionista el ID del recepcionista a eliminar
     * @return ResponseEntity con un mensaje de éxito
     * @throws EntityNotFoundException 
     * @throws IllegalOperationException 
     */
    @DeleteMapping("/{idRecepcionista}")
    public ResponseEntity<?> eliminarRecepcionista(@PathVariable Long idRecepcionista) throws EntityNotFoundException, IllegalOperationException {
        recepcionistaService.eliminarRecepcionista(idRecepcionista);
        ApiResponse<String> response = new ApiResponse<>(true, "Recepcionista eliminado con éxito", null);
        return ResponseEntity.ok(response);
    }
    /**
     * Asignar reserva a un recepcionista.
     *
     * @param idRecepcionista del recepcionista
     * @param idReserva de la reserva
     * @return ResponseEntity con un mensaje de éxito
     * @throws EntityNotFoundException 
     * @throws IllegalOperationException 
     */
    @PutMapping(value = "asignarReserva/{idRecepcionista}/{idReserva}")
    public ResponseEntity<?> asignarReserva(@PathVariable Long idRecepcionista, @PathVariable Long idReserva) throws EntityNotFoundException, IllegalOperationException {
        Recepcionista recepcionista = recepcionistaService.asignarReserva(idRecepcionista, idReserva);
        RecepcionistaDTO recepcionistaDTO = modelMapper.map(recepcionista, RecepcionistaDTO.class);
        ApiResponse<RecepcionistaDTO> response = new ApiResponse<>(true, "Reserva asignada con éxito", recepcionistaDTO);
        return ResponseEntity.ok(response);
    }
}
