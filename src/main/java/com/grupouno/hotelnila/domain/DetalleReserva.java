/*
 * @file DetalleReserva.java;
 * @Autor (c)2024 JuanRuiz
 * @Created 5 mar. 2024,03:08:08
 */
package com.grupouno.hotelnila.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/**
 * The Class DetalleReserva.
 */
@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idDetalleReserva")
public class DetalleReserva {
	
	/** The id detalle reserva. */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleReserva;

    /** The pago reserva. */
    private float pagoReserva;
    
    /** The estado reserva. */
    private boolean estadoReserva;

    /** The habitacion. */
    @ManyToOne
    //@JsonBackReference
    @JoinColumn(name = "id_habitacion")
    private Habitacion habitacion;

    /** The reserva. */
    @ManyToOne
    //@JsonBackReference
    @JoinColumn(name = "id_reserva")
    private Reserva reserva;
}
