/*
 * @file ClienteService.java;
 * @Autor (c)2024 JhenniferMendoza
 * @Created 3 mar. 2024,10:50:54
 */
package com.grupouno.hotelnila.services;

import java.util.List;

import com.grupouno.hotelnila.domain.Cliente;
import com.grupouno.hotelnila.domain.DetalleReserva;
import com.grupouno.hotelnila.domain.Habitacion;
import com.grupouno.hotelnila.domain.Reserva;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.IllegalOperationException;

/**
 * Interfaz que define las operaciones de servicio relacionadas con los clientes.
 */
public interface ClienteService {
	
	/**
	  * Lista todos los clientes registrados.
	  *
	  * @return Lista de clientes
	  */
	List<Cliente>listarClientes();

	/**
	 * Busca un cliente por su id.
	 *
	 * @param idCliente el id del cliente a buscar
	 * @return El cliente encontrado
	 * @throws EntityNotFoundException Si no se encuentra el cliente con el ID proporcionado.
	 */
    Cliente buscarPorIdCliente(Long idCliente) throws EntityNotFoundException;
    
    /**
	 * Crea un nuevo cliente.
	 *
	 * @param cliente El cliente que va a ser creado
	 * @return El cliente creado
	 * @throws IllegalOperationException Si el DNI del cliente ya esta registrado en el sistema.S
	 */
    Cliente crearCliente (Cliente cliente)throws IllegalOperationException;
    
    /**
	* Actualiza la informacion de un cliente existente en el sistema.
	*
	* @param idCliente El ID del cliente que se desea actualizar.
	* @param cliente   El objeto Cliente con la informacion actualizada.
	* @return El cliente actualizado.
	* @throws EntityNotFoundException    Si no se encuentra el cliente con el ID proporcionado.
	* @throws IllegalOperationException Si el DNI del cliente ya esta registrado en otro cliente.
	 */
    Cliente actualizarCliente(Long idCliente, Cliente cliente) throws EntityNotFoundException, IllegalOperationException;
    
    /**
	 * Elimina un cliente .
	 *
	 * @param idCliente El ID del cliente que se desea eliminar.
	 * @throws EntityNotFoundException    Si no se encuentra el cliente con el ID proporcionado.
	 * @throws IllegalOperationException Si el cliente tiene reservas asignadas y no puede ser eliminado.
	 */
    void eliminarCliente(Long idCliente) throws EntityNotFoundException, IllegalOperationException;
    
    /**
	 * Asigna una direccion a un cliente.
	 *
	 * @param idCliente  El ID del cliente al que se le asignara la direccion.
	 * @param idDireccion El ID de la direccion que se asignara al cliente.
	 * @return El cliente con la direccion asignada.
	 * @throws EntityNotFoundException    Si no se encuentra el cliente o la direccion con los IDs proporcionados.
	 * @throws IllegalOperationException Si la direccion ya tiene un cliente asignado.
	 */
    Cliente asignarDireccion(Long idCliente, Long idDireccion) throws EntityNotFoundException, IllegalOperationException;
    
    /**
	 * Obtiene la lista de reservas asociadas a un cliente.
	 *
	 * @param idCliente El ID del cliente del cual se desean obtener las reservas.
	 * @return Una lista de reservas asociadas al cliente.
	 * @throws EntityNotFoundException Si no se encuentra el cliente con el ID proporcionado.
	 */
    List<Reserva> obtenerReservas(Long idCliente) throws EntityNotFoundException;
    
    /**
	 * Obtiene una reserva especifica asociada a un cliente.
	 *
	 * @param idCliente El ID del cliente al que esta asociada la reserva.
	 * @param idReserva El ID de la reserva que se desea obtener.
	 * @return La reserva especifica asociada al cliente.
	 * @throws EntityNotFoundException    Si no se encuentra el cliente o la reserva con los IDs proporcionados.
	 * @throws IllegalOperationException Si la reserva no fue realizada por el cliente.
	 */
    Reserva obtenerReservaPorId(Long idCliente,Long idReserva) throws EntityNotFoundException,IllegalOperationException ;
    
    /**
	 * Obtiene la lista de habitaciones asociadas a una reserva de un cliente.
	 *
	 * @param idCliente El ID del cliente al que esta asociada la reserva.
	 * @param idReserva El ID de la reserva de la cual se desean obtener las habitaciones.
	 * @return Una lista de habitaciones asociadas a la reserva del cliente.
	 * @throws EntityNotFoundException    Si no se encuentra el cliente, la reserva o alguna habitacion asociada.
	 * @throws IllegalOperationException Si la reserva no pertenece al cliente o no tiene habitaciones.
	 */
    List<Habitacion> obtenerHabitaciones (Long idCliente,Long idReserva)throws EntityNotFoundException,IllegalOperationException;
    
    /**
	 * Obtiene una habitación específica asociada a una reserva de un cliente.
	 *
	 * @param idCliente    El ID del cliente al que esta asociada la reserva.
	 * @param idReserva    El ID de la reserva de la cual se desea obtener la habitacion.
	 * @param idHabitacion El ID de la habitacion que se desea obtener.
	 * @return La habitación específica asociada a la reserva del cliente.
	 * @throws EntityNotFoundException    Si no se encuentra el cliente, la reserva o la habitacion con los IDs proporcionados.
	 * @throws IllegalOperationException Si la reserva no pertenece al cliente, no tiene habitaciones o la habitacion no esta asociada a la reserva.
	 */
    Habitacion obtenerHabitacionPorId(Long idCliente,Long idReserva, Long idHabitacion)throws EntityNotFoundException,IllegalOperationException;

}
