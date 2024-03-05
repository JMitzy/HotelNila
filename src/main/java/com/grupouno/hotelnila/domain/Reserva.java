/*
 * @file Reserva.java;
 * @Autor (c)2024 JuanRuiz
 * @Created 5 mar. 2024,03:08:31
 */
package com.grupouno.hotelnila.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * The Class Reserva.
 */
@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idReserva")
public class Reserva {
    
    /** The id reserva. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReserva;
    
    /** The fecha inicio. */
    @Temporal(TemporalType.DATE)
    @NotNull(message = "La fecha de inicio no puede ser nula")
    private Date fechaInicio;
    
    /** The fecha fin. */
    @Temporal(TemporalType.DATE)
    @NotNull(message = "La fecha de fin no puede ser nula")
    private Date fechaFin;

   /** The reserva habitacion. */
   @OneToMany(mappedBy = "reserva")
   //@JsonManagedReference
   private List<DetalleReserva> reserva_habitacion = new ArrayList<>();
   
    /** The recepcionista. */
    @ManyToOne
    //@JsonBackReference
    private Recepcionista recepcionista;

    /** The cliente. */
    @ManyToOne
    //@JsonBackReference
    private Cliente cliente;
    
    /** The comprobante. */
    @OneToOne
    //@JsonManagedReference
    private Comprobante comprobante;

}
