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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupouno.hotelnila.domain.Factura;
import com.grupouno.hotelnila.dto.FacturaDTO;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.IllegalOperationException;
import com.grupouno.hotelnila.services.FacturaService;
import com.grupouno.hotelnila.util.ApiResponse;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {
	
	@Autowired
	private FacturaService facturaService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public ResponseEntity<?> listarFaturas(){
        List<Factura> facturas = facturaService.listarFacturas();
        List<FacturaDTO> facturaDTOs = facturas.stream().map(factura->modelMapper.map(factura, FacturaDTO.class))
                .collect(Collectors.toList());
        ApiResponse<List<FacturaDTO>> response = new ApiResponse<>(true, "Lista de facturas obtenida con éxito", facturaDTOs);
        return ResponseEntity.ok(response);
    }
	
	@GetMapping("/{idFactura}")
	public ResponseEntity<?> listarPorID(@PathVariable Long idFactura) throws EntityNotFoundException {
        Factura facturas = facturaService.buscarPorIdFactura(idFactura);
        FacturaDTO facturaDTO = modelMapper.map(facturas, FacturaDTO.class);
        ApiResponse<FacturaDTO> response = new ApiResponse<>(true, "Factura obtenida con éxito", facturaDTO);
        return ResponseEntity.ok(response);
    }
	
	@PostMapping
	public ResponseEntity<?> crearFactura(@RequestBody FacturaDTO facturaDTO) throws IllegalOperationException {
        Factura factura = modelMapper.map(facturaDTO, Factura.class);
        facturaService.crearFactura(factura);
        FacturaDTO savedFacturaDTO = modelMapper.map(factura, FacturaDTO.class);
        ApiResponse<FacturaDTO> response = new ApiResponse<>(true, "Factura creada con éxito", savedFacturaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
	
	@DeleteMapping("/{idFatura}")
	public ResponseEntity<?> eliminarFactura(@PathVariable Long idFactura) throws EntityNotFoundException, IllegalOperationException {
        facturaService.eliminarFactura(idFactura);
        ApiResponse<String> response = new ApiResponse<>(true, "Factura eliminada con éxito", null);
        return ResponseEntity.ok(response);
    }
}
