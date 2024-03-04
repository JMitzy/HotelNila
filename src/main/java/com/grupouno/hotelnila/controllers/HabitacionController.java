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
import com.grupouno.hotelnila.domain.Habitacion;
import com.grupouno.hotelnila.dto.HabitacionDTO;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.IllegalOperationException;
import com.grupouno.hotelnila.services.HabitacionService;
import com.grupouno.hotelnila.util.ApiResponse;

@RestController
@RequestMapping("/api/habitaciones")
public class HabitacionController {
	@Autowired
    private HabitacionService habiService;

    /** ModelMapper para mapeo de DTOs. */
    @Autowired
    private ModelMapper modelMapper;

	/**
	 * Obtiene una lista de todas las habitaciones.
     *
     * @return ResponseEntity con la lista de habitaciones y un mensaje de éxito
	 */   
	@GetMapping
    public ResponseEntity<?> listarHabitaciones(){
        List<Habitacion> habitaciones = habiService.listarHabitaciones();
        List<HabitacionDTO> habitacionDTOs = habitaciones.stream().map(habitacion->modelMapper.map(habitacion, HabitacionDTO.class))
                .collect(Collectors.toList());
        ApiResponse<List<HabitacionDTO>> response = new ApiResponse<>(true, "Lista de habitaciones obtenida con éxito", habitacionDTOs);
        return ResponseEntity.ok(response);
    }
	
	
	/**
	* Obtiene una habitacion por su ID.
     *
     * @param ID de la habitacion a buscar
     * @return ResponseEntity con la habitación encontrada y un mensaje de éxito
     * @throws EntityNotFoundException
	 */
	@GetMapping("/{idHabitacion}")
    public ResponseEntity<?> listarPorID(@PathVariable Long idHabitacion) throws EntityNotFoundException {
		Habitacion habitaciones = habiService.buscarPorIdHabitacion(idHabitacion);
		HabitacionDTO habitacionDTO = modelMapper.map(habitaciones, HabitacionDTO.class);
        ApiResponse<HabitacionDTO> response = new ApiResponse<>(true, "Habitación obtenida con éxito", habitacionDTO);
        return ResponseEntity.ok(response);
    }
	
	/**
	 * Crea una nueva habitación.
     *
     * @param  DTO de la habitación a crear
     * @return ResponseEntity con la habitación creada y un mensaje de éxito
     * @throws IllegalOperationException
	 */
	@PostMapping
    public ResponseEntity<?> crearCliente(@RequestBody HabitacionDTO habitacionDTO) throws IllegalOperationException {
		Habitacion habitacion = modelMapper.map(habitacionDTO, Habitacion.class);
        habiService.crearHabitacion(habitacion);
        HabitacionDTO savedHabitacionDTO = modelMapper.map(habitacion, HabitacionDTO.class);
        ApiResponse<HabitacionDTO> response = new ApiResponse<>(true, "Habitación creada con éxito", savedHabitacionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	/**
	 * Actualizar habitacion.
	 *
	 * @param id de la habitacion
	 * @param  Información actualizada de la habitacion
     * @return La habitación actualizada
     * @throws EntityNotFoundException 
     * @throws IllegalOperationException
	 */
	@PutMapping("/{idHabitacion}")
    public ResponseEntity<?> actualizarHabitacion(@PathVariable Long idHabitacion, @RequestBody HabitacionDTO habitacionDTO) throws EntityNotFoundException, IllegalOperationException {
		Habitacion habitacion = modelMapper.map(habitacionDTO, Habitacion.class);
        habiService.actualizarHabitacion(idHabitacion, habitacion);
        HabitacionDTO updatedHabitacionDTO = modelMapper.map(habitacion, HabitacionDTO.class);
        ApiResponse<HabitacionDTO> response = new ApiResponse<>(true, "Habitación actualizada con con éxito",updatedHabitacionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    } 

}
