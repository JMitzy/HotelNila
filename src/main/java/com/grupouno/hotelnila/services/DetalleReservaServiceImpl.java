/*
 * @file DetalleReservaServiceImpl.java;
 * @Autor (c)2024 JuanRuiz
 * @Created 5 mar. 2024,03:10:49
 */
package com.grupouno.hotelnila.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupouno.hotelnila.domain.DetalleReserva;
import com.grupouno.hotelnila.domain.Habitacion;
import com.grupouno.hotelnila.domain.Reserva;
import com.grupouno.hotelnila.exception.EntityNotFoundException;
import com.grupouno.hotelnila.exception.ErrorMessage;
import com.grupouno.hotelnila.exception.IllegalOperationException;
import com.grupouno.hotelnila.repository.DetalleReservaRepository;
import com.grupouno.hotelnila.repository.HabitacionRepository;
import com.grupouno.hotelnila.repository.ReservaRepository;

import jakarta.transaction.Transactional;

// TODO: Auto-generated Javadoc
/**
 * The Class DetalleReservaServiceImpl.
 */
@Service
public class DetalleReservaServiceImpl implements DetalleReservaService{
    
    /** The det res rep. */
    @Autowired
    private DetalleReservaRepository detResRep;

    /** The rsrv rep. */
    @Autowired
    private ReservaRepository rsrvRep;

    /** The hab rep. */
    @Autowired
    private HabitacionRepository habRep;

    /**
     * Listar detalles reservas.
     *
     * @return the list
     */
    @Override
    @Transactional
    public List<DetalleReserva> listarDetallesReservas() {
        return (List<DetalleReserva>) detResRep.findAll();
    }

    /**
     * Buscar por id detalle reserva.
     *
     * @param idReservaHabitacion the id reserva habitacion
     * @return the detalle reserva
     * @throws EntityNotFoundException the entity not found exception
     */
    @Override
    @Transactional
    public DetalleReserva buscarPorIdDetalleReserva(Long idReservaHabitacion) throws EntityNotFoundException {
        Optional<DetalleReserva> detallereservas = detResRep.findById(idReservaHabitacion);
        if (detallereservas.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessage.DETALLEHABITACION_NOT_FOUND);
        }
        return detallereservas.get();
    }

    /**
     * Crear detalle reserva.
     *
     * @param detalleReserva the detalle reserva
     * @return the detalle reserva
     * @throws IllegalOperationException the illegal operation exception
     */
    @Override
    public DetalleReserva crearDetalleReserva(DetalleReserva detalleReserva) throws IllegalOperationException {
        return detResRep.save(detalleReserva);
    }

    /**
     * Actualizar detalle reserva.
     *
     * @param idReservaHabitacion the id reserva habitacion
     * @param detalleReserva the detalle reserva
     * @return the detalle reserva
     * @throws EntityNotFoundException the entity not found exception
     * @throws IllegalOperationException the illegal operation exception
     */
    @Override
    public DetalleReserva actualizarDetalleReserva(Long idReservaHabitacion, DetalleReserva detalleReserva) throws EntityNotFoundException, IllegalOperationException {
        Optional<DetalleReserva> detallereservas = detResRep.findById(idReservaHabitacion);
        if(detallereservas.isEmpty()){
            throw new EntityNotFoundException(ErrorMessage.DETALLEHABITACION_NOT_FOUND);
        }
        detalleReserva.setIdDetalleReserva(detalleReserva.getIdDetalleReserva());
        return detResRep.save(detalleReserva);
    }

    /**
     * Asignar reserva.
     *
     * @param idReservaHabitacion the id reserva habitacion
     * @param idReserva the id reserva
     * @return the detalle reserva
     * @throws EntityNotFoundException the entity not found exception
     * @throws IllegalOperationException the illegal operation exception
     */
    @Override
    public DetalleReserva asignarReserva(Long idReservaHabitacion, Long idReserva) throws EntityNotFoundException, IllegalOperationException {
        DetalleReserva detreserva = detResRep.findById(idReservaHabitacion).orElseThrow(
                ()->new EntityNotFoundException(ErrorMessage.DETALLEHABITACION_NOT_FOUND)
        );

        Reserva reserva = rsrvRep.findById(idReserva).orElseThrow(
                ()->new EntityNotFoundException(ErrorMessage.RESERVA_NOT_FOUND)
        );

        if (reserva.getReserva_habitacion() != null && !reserva.getReserva_habitacion().isEmpty()) {
            throw new IllegalOperationException("El detalle reserva ya tiene una reserva asignada.");
        }

        detreserva.setReserva(reserva);
        detResRep.save(detreserva);
        return detreserva;
    }

    /**
     * Asignar habitacion.
     *
     * @param idReservaHabitacion the id reserva habitacion
     * @param idHabitacion the id habitacion
     * @return the detalle reserva
     * @throws EntityNotFoundException the entity not found exception
     * @throws IllegalOperationException the illegal operation exception
     */
    @Override
    public DetalleReserva asignarHabitacion(Long idReservaHabitacion, Long idHabitacion) throws EntityNotFoundException, IllegalOperationException {
        DetalleReserva detReserva = detResRep.findById(idReservaHabitacion).orElseThrow(
                ()->new EntityNotFoundException(ErrorMessage.DETALLEHABITACION_NOT_FOUND)
        );

        Habitacion habitacion = habRep.findById(idHabitacion).orElseThrow(
                ()->new EntityNotFoundException(ErrorMessage.HABITACION_NOT_FOUND)
        );

        if(habitacion.getReserva_habitacion()!= null && !habitacion.getReserva_habitacion().isEmpty()){
            throw new IllegalOperationException("El detalle reserva ya tiene una habitación asignada.");
        }

        detReserva.setHabitacion(habitacion);
        detResRep.save(detReserva);
        return detReserva;
    }
}
