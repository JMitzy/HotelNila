/*
 * @file PedidoController.java;
 * @Autor (c)2024 AndersonDietrich
 * @Created 4 mar. 2024,00:28:37
 */

package com.grupouno.hotelnila.controllers;import java.util.List;
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

import com.grupouno.hotelnila.domain.Pedido;
import com.grupouno.hotelnila.dto.PedidoDTO;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.IllegalOperationException;
import com.grupouno.hotelnila.services.PedidoService;
import com.grupouno.hotelnila.util.ApiResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class PedidoController.
 */
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
	
	/** The pedido service. */
	@Autowired
	private PedidoService pedidoService;
	
	/** The model mapper. */
	@Autowired
	private ModelMapper modelMapper;
	
	/**
	 * Listar pedidos.
	 *
	 * @return the response entity
	 */
	@GetMapping
	public ResponseEntity<?> listarPedidos(){
		List<Pedido> pedidos = pedidoService.listarPedidos();
		List<PedidoDTO> pedidoDTOs = pedidos.stream().map(pedido->modelMapper.map(pedido, PedidoDTO.class)).collect(Collectors.toList());
		ApiResponse<List<PedidoDTO>> response = new ApiResponse<>(true,"Lista de facturas obtenida con éxito",pedidoDTOs);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Listar por ID.
	 *
	 * @param idPedido the id pedido
	 * @return the response entity
	 * @throws EntityNotFoundException the entity not found exception
	 */
	@GetMapping("/{idPedido}")
	public ResponseEntity<?>listarPorID(@PathVariable Long idPedido) throws EntityNotFoundException {
		Pedido pedidos = pedidoService.buscarPorIdPedido(idPedido);
		PedidoDTO pedidoDTO = modelMapper.map(pedidos, PedidoDTO.class);
		ApiResponse<PedidoDTO> response = new ApiResponse<>(true,"Pedido obtenido con éxito", pedidoDTO);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Crear pedido.
	 *
	 * @param pedidoDTO the pedido DTO
	 * @return the response entity
	 * @throws IllegalOperationException the illegal operation exception
	 */
	@PostMapping
	public ResponseEntity<?> crearPedido(@RequestBody PedidoDTO pedidoDTO) throws IllegalOperationException {
		Pedido pedido = modelMapper.map(pedidoDTO, Pedido.class);
		pedidoService.crearPedido(pedido);
		PedidoDTO savedPedidoDTO = modelMapper.map(pedido, PedidoDTO.class);
		ApiResponse<PedidoDTO> response = new ApiResponse<>(true,"Pedido creado con éxito", savedPedidoDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	/**
	 * Eliminar pedido.
	 *
	 * @param idPedido the id pedido
	 * @return the response entity
	 * @throws EntityNotFoundException the entity not found exception
	 * @throws IllegalOperationException the illegal operation exception
	 */
	@DeleteMapping("/{idPedido}")
	public ResponseEntity<?> eliminarPedido(@PathVariable Long idPedido)throws EntityNotFoundException, IllegalOperationException {
		pedidoService.eliminarPedido(idPedido);
		ApiResponse<String> response = new ApiResponse<>(true, "Pedido eliminado con éxito", null);
		return ResponseEntity.ok(response);
	}
	
}
