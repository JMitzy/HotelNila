/*
 * @file Habitacion.java;
 * @Autor (c)2024 JuanRuiz
 * @Created 5 mar. 2024,03:08:46
 */
package com.grupouno.hotelnila.domain;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idHabitacion")
public class Habitacion extends RepresentationModel<Habitacion>{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHabitacion;
    
   
    private String nroHabitacion;
   
    private String tipoHabitacion;
    
    private String estado;
    

    private Float precio;

    @ManyToMany
    private List<Reserva> reserva = new ArrayList<>();
    
    /** The reserva habitacion. */
    @OneToMany(mappedBy = "habitacion")
    //@JsonManagedReference
    private List<DetalleReserva> reserva_habitacion = new ArrayList<>();
}