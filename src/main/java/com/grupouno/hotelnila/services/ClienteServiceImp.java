/*
 * @file ClienteServiceImp.java;
 * @Autor (c)2024 JhenniferMendoza
 * @Created 3 mar. 2024,10:52:04
 */
package com.grupouno.hotelnila.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.grupouno.hotelnila.domain.Cliente;
import com.grupouno.hotelnila.domain.DetalleReserva;
import com.grupouno.hotelnila.domain.Direccion;
import com.grupouno.hotelnila.domain.Habitacion;
import com.grupouno.hotelnila.domain.Reserva;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.ErrorMessage;
import com.grupouno.hotelnila.exception.IllegalOperationException;
import com.grupouno.hotelnila.repository.ClienteRepository;
import com.grupouno.hotelnila.repository.DireccionRepository;
import com.grupouno.hotelnila.repository.HabitacionRepository;
import com.grupouno.hotelnila.repository.ReservaRepository;


/**
 * Implementacion del servicio para operaciones relacionadas con los clientes.
 */
@Service
public class ClienteServiceImp implements ClienteService {
	
	@Autowired
	private ClienteRepository cliRep;
	
	@Autowired
	private DireccionRepository direcRep;
	
	@Autowired
	private ReservaRepository resRep;
	
	@Autowired
	private HabitacionRepository habRep;
	

	/**
	  * Lista todos los clientes registrados.
     *
     * @return Lista de clientes
	 */
	@Override
	@Transactional
	public List<Cliente> listarClientes() {
		return (List<Cliente>) cliRep.findAll();
	}

	/**
	 * Busca un cliente por su id.
	 *
	 * @param idCliente el id del cliente a buscar
	 * @return El cliente encontrado
	 * @throws EntityNotFoundException Si no se encuentra el cliente con el ID proporcionado.
	 */
	@Override
	@Transactional
	public Cliente buscarPorIdCliente(Long idCliente) throws EntityNotFoundException {
		Optional<Cliente> clientes = cliRep.findById(idCliente);
        if (clientes.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessage.CLIENTE_NOT_FOUND);
        }
        
        return clientes.get();
	}

	/**
	 * Crea un nuevo cliente.
	 *
	 * @param cliente El cliente que va a ser creado
	 * @return El cliente creado
	 * @throws IllegalOperationException Si el DNI del cliente ya esta registrado en el sistema.S
	 */
	@Override
	@Transactional
	public Cliente crearCliente(Cliente cliente) throws IllegalOperationException {
		
		if(!cliRep.findBydni(cliente.getDni()).isEmpty()) {
		    throw new IllegalOperationException("El DNI del cliente ya está registrado.");
		}
		return cliRep.save(cliente);
	}

	/**
	* Actualiza la informacion de un cliente existente en el sistema.
	*
	* @param idCliente El ID del cliente que se desea actualizar.
	* @param cliente   El objeto Cliente con la informacion actualizada.
	* @return El cliente actualizado.
	* @throws EntityNotFoundException    Si no se encuentra el cliente con el ID proporcionado.
	* @throws IllegalOperationException Si el DNI del cliente ya esta registrado en otro cliente.
	 */
	@Override
	@Transactional
	public Cliente actualizarCliente(Long idCliente, Cliente cliente)
			throws EntityNotFoundException, IllegalOperationException {
		Optional<Cliente> clienteEntity = cliRep.findById(idCliente);
        if(clienteEntity.isEmpty()){
            throw new EntityNotFoundException(ErrorMessage.CLIENTE_NOT_FOUND);
        }
        if(!cliRep.findBydni(cliente.getDni()).isEmpty()){
            throw new IllegalOperationException("El DNI del cliente ya está registrado.");
        }
        cliente.setIdCliente(idCliente);
        return cliRep.save(cliente);
	}

	/**
	 * Elimina un cliente .
	 *
	 * @param idCliente El ID del cliente que se desea eliminar.
	 * @throws EntityNotFoundException    Si no se encuentra el cliente con el ID proporcionado.
	 * @throws IllegalOperationException Si el cliente tiene reservas asignadas y no puede ser eliminado.
	 */
	@Override
	@Transactional
	public void eliminarCliente(Long idCliente) throws EntityNotFoundException, IllegalOperationException {
		Cliente cliente = cliRep.findById(idCliente).orElseThrow(
                ()->new EntityNotFoundException(ErrorMessage.CLIENTE_NOT_FOUND)
        );
        if(!cliente.getReservas().isEmpty()){
          throw new IllegalOperationException("El cliente tiene reservas asignadas");
       }
        cliRep.deleteById(idCliente);

	}

	/**
	 * Asigna una direccion a un cliente.
	 *
	 * @param idCliente  El ID del cliente al que se le asignara la direccion.
	 * @param idDireccion El ID de la direccion que se asignara al cliente.
	 * @return El cliente con la direccion asignada.
	 * @throws EntityNotFoundException    Si no se encuentra el cliente o la direccion con los IDs proporcionados.
	 * @throws IllegalOperationException Si la direccion ya tiene un cliente asignado.
	 */
	@Override
	@Transactional
	public Cliente asignarDireccion(Long idCliente, Long idDireccion)
			throws EntityNotFoundException, IllegalOperationException {
		Cliente cliente = cliRep.findById(idCliente).orElseThrow(
                ()->new EntityNotFoundException(ErrorMessage.CLIENTE_NOT_FOUND)
        );

        Direccion direccion = direcRep.findById(idDireccion).orElseThrow(
                ()->new EntityNotFoundException(ErrorMessage.DIRECCION_NOT_FOUND)
        );

        // Verificar si la direccion ya tiene un cliente asignado
        if (direccion.getCliente() != null) {
            throw new IllegalOperationException("La dirección ya tiene un cliente asignado.");
        }

        // Asignar la direccion al cliente
        cliente.setDirec(direccion);

        // Guardar los cambios en la base de datos
       cliRep.save(cliente);

        return cliente;
	}
	
	/**
	 * Obtiene la lista de reservas asociadas a un cliente.
	 *
	 * @param idCliente El ID del cliente del cual se desean obtener las reservas.
	 * @return Una lista de reservas asociadas al cliente.
	 * @throws EntityNotFoundException Si no se encuentra el cliente con el ID proporcionado.
	 */
	@Override
	public List<Reserva> obtenerReservas(Long idCliente) throws EntityNotFoundException {
		Cliente cliente = cliRep.findById(idCliente)
				.orElseThrow(() -> new EntityNotFoundException(ErrorMessage.CLIENTE_NOT_FOUND));
		return cliente.getReservas();
	}
	/**
	 * Obtiene una reserva especifica asociada a un cliente.
	 *
	 * @param idCliente El ID del cliente al que esta asociada la reserva.
	 * @param idReserva El ID de la reserva que se desea obtener.
	 * @return La reserva especifica asociada al cliente.
	 * @throws EntityNotFoundException    Si no se encuentra el cliente o la reserva con los IDs proporcionados.
	 * @throws IllegalOperationException Si la reserva no fue realizada por el cliente.
	 */
	
	@Override
	public Reserva obtenerReservaPorId(Long idCliente, Long idReserva)
			throws EntityNotFoundException, IllegalOperationException {
		Cliente cliente = cliRep.findById(idCliente)
				.orElseThrow(() -> new EntityNotFoundException(ErrorMessage.CLIENTE_NOT_FOUND));
		Reserva reserva = resRep.findById(idReserva)
				.orElseThrow(() -> new EntityNotFoundException(ErrorMessage.RESERVA_NOT_FOUND));
		List<Reserva> reservasCliente = cliente.getReservas();

		if (reservasCliente.contains(reserva)) {
			return reserva;
		} else {
			throw new IllegalOperationException("La reserva no fue realizada por el cliente");
		}
	}


	/**
	 * Obtiene la lista de habitaciones asociadas a una reserva de un cliente.
	 *
	 * @param idCliente El ID del cliente al que esta asociada la reserva.
	 * @param idReserva El ID de la reserva de la cual se desean obtener las habitaciones.
	 * @return Una lista de habitaciones asociadas a la reserva del cliente.
	 * @throws EntityNotFoundException    Si no se encuentra el cliente, la reserva o alguna habitacion asociada.
	 * @throws IllegalOperationException Si la reserva no pertenece al cliente o no tiene habitaciones.
	 */
	@Override
	public List<Habitacion> obtenerHabitaciones(Long idCliente, Long idReserva)
			throws EntityNotFoundException, IllegalOperationException {
		Cliente cliente = cliRep.findById(idCliente)
				.orElseThrow(() -> new EntityNotFoundException(ErrorMessage.CLIENTE_NOT_FOUND));
		Reserva reserva = resRep.findById(idReserva)
				.orElseThrow(() -> new EntityNotFoundException(ErrorMessage.RESERVA_NOT_FOUND));
		List<Reserva> reservasCliente = cliente.getReservas();
		List<Habitacion> habitaciones = new ArrayList<>();

		if (reservasCliente.contains(reserva)) {

			for (DetalleReserva detalle : reserva.getReserva_habitacion()) {

				habitaciones.add(detalle.getHabitacion());
			}
			if (habitaciones.isEmpty()) {
				throw new IllegalOperationException("La reserva no tiene habitaciones");
			} else {
				return habitaciones;
			}
		} else {
			throw new IllegalOperationException("La reserva no pertenece al cliente");
		}

	}

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
	@Override
	public Habitacion obtenerHabitacionPorId(Long idCliente, Long idReserva, Long idHabitacion)
			throws EntityNotFoundException, IllegalOperationException {
		Cliente cliente = cliRep.findById(idCliente)
				.orElseThrow(() -> new EntityNotFoundException(ErrorMessage.CLIENTE_NOT_FOUND));
		Reserva reserva = resRep.findById(idReserva)
				.orElseThrow(() -> new EntityNotFoundException(ErrorMessage.RESERVA_NOT_FOUND));
		Habitacion habitacion = habRep.findById(idHabitacion)
				.orElseThrow(() -> new EntityNotFoundException(ErrorMessage.HABITACION_NOT_FOUND));
		List<Reserva> reservasCliente = cliente.getReservas();
		List<Habitacion> habitaciones = new ArrayList<>();

		if (reservasCliente.contains(reserva)) {

			for (DetalleReserva detalle : reserva.getReserva_habitacion()) {

				habitaciones.add(detalle.getHabitacion());
			}
				if (habitaciones.contains(habitacion)){
					return habitacion;
				}else {
					throw new IllegalOperationException("La habitación no pertenece a la reserva");
				}
			}
			if (habitaciones.isEmpty()) {
				throw new IllegalOperationException("La reserva no tiene habitaciones");
		} else {
			throw new IllegalOperationException("La reserva no pertenece al cliente");
		}

	}
}

	
