/*
 * @file ClienteServiceImp.java;
 * @Autor (c)2024 JhenniferMendoza
 * @Created 3 mar. 2024,10:52:04
 */
package com.grupouno.hotelnila.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupouno.hotelnila.domain.Cliente;
import com.grupouno.hotelnila.domain.Direccion;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.ErrorMessage;
import com.grupouno.hotelnila.exception.IllegalOperationException;
import com.grupouno.hotelnila.repository.ClienteRepository;
import com.grupouno.hotelnila.repository.DireccionRepository;
import com.grupouno.hotelnila.repository.ReservaRepository;



/**
 * La clase ClienteServiceImp es una implementación de la interfaz ClienteService
 */
@Service
public class ClienteServiceImp implements ClienteService {
	
	@Autowired
	private ClienteRepository cliRep;
	
	@Autowired
	private DireccionRepository direcRep;
	

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
	* Busca un cliente por su identificador único.
     *
     * @param Identificador del cliente a buscar
     * @return El cliente encontrado
     * @throws EntityNotFoundException
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
	* Crea un nuevo cliente en el sistema.
     *
     * @param El cliente a crear
     * @return El cliente creado
     * @throws IllegalOperationException
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
	 * Actualiza la información de un cliente existente.
     *
     * @param Identificador del cliente a actualizar
     * @param Los datos actualizados del cliente
     * @return El cliente actualizado
     * @throws EntityNotFoundException
     * @throws IllegalOperationException
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
	 * Elimina un cliente.
     *
     * @param Identificador del cliente a eliminar
     * @throws EntityNotFoundException 
     * @throws IllegalOperationException
	 */
	@Override
	@Transactional
	public void eliminarCliente(Long idCliente) throws EntityNotFoundException, IllegalOperationException {
		Cliente cliente = cliRep.findById(idCliente).orElseThrow(
                ()->new EntityNotFoundException(ErrorMessage.CLIENTE_NOT_FOUND)
        );
        //if(!cliente.getReservas().isEmpty()){
          //  throw new IllegalOperationException("El cliente tiene reservas asignadas");
        //}
        cliRep.deleteById(idCliente);

	}

	
	@Override
	@Transactional
	public Cliente asignarDirección(Long idCliente, Long idDireccion)
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

	

}
