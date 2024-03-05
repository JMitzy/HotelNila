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

import com.grupouno.hotelnila.domain.Comprobante;
import com.grupouno.hotelnila.dto.ComprobanteDTO;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.IllegalOperationException;
import com.grupouno.hotelnila.services.ComprobanteService;
import com.grupouno.hotelnila.util.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/comprobantes")
public class ComprobanteController {
	

	@Autowired
	private ComprobanteService comprobanteService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public ResponseEntity<?> listarComprobantes(){
        List<Comprobante> comprobantes = comprobanteService.listarComprobantes();
        List<ComprobanteDTO> comprobanteDTOs = comprobantes.stream().map(comprobante->modelMapper.map(comprobante, ComprobanteDTO.class))
                .collect(Collectors.toList());
        ApiResponse<List<ComprobanteDTO>> response = new ApiResponse<>(true, "Lista de comprobantes obtenida con éxito", comprobanteDTOs);
        return ResponseEntity.ok(response);
    }
	
	@GetMapping("/{idComprobante}")
	public ResponseEntity<?> listarPorID(@PathVariable Long idComprobante) throws EntityNotFoundException {
        Comprobante comprobantes = comprobanteService.buscarPorIdComprobante(idComprobante);
        ComprobanteDTO comprobanteDTO = modelMapper.map(comprobantes, ComprobanteDTO.class);
        ApiResponse<ComprobanteDTO> response = new ApiResponse<>(true, "Comprobante obtenido con éxito", comprobanteDTO);
        return ResponseEntity.ok(response);
    }
	
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
	
	@DeleteMapping("/{idComprobante}")
	public ResponseEntity<?> eliminarComprobante(@PathVariable Long idComprobante) throws EntityNotFoundException, IllegalOperationException {
		comprobanteService.eliminarComprobante(idComprobante);
        ApiResponse<String> response = new ApiResponse<>(true, "Comprobante eliminada con éxito", null);
        return ResponseEntity.ok(response);
    }
	
	@PutMapping(value = "/asignarReserva/{idComprobante}/{idReserva}")
    public ResponseEntity<?> asignarReserva (@PathVariable Long idComprobante, @PathVariable Long idReserva) throws EntityNotFoundException, IllegalOperationException {
        Comprobante comprobante = comprobanteService.asignarReserva(idComprobante, idReserva);
        ComprobanteDTO comprobanteDTO = modelMapper.map(comprobante, ComprobanteDTO.class);
        ApiResponse<ComprobanteDTO> response = new ApiResponse<>(true, "Reserva asignada con éxito", comprobanteDTO);
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
