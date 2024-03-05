/*
 * @file Habitacion.java;
 * @Autor (c)2024 JuanRuiz
 * @Created 5 mar. 2024,03:08:46
 */
package com.grupouno.hotelnila.domain;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idHabitacion")
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHabitacion;
    
    @NotNull(message = "El número de habitación no puede ser nula")
    private String nroHabitacion;
    @NotBlank(message = "Tipo de habitación no pueden estar en blanco")
    private String tipoHabitacion;
    @NotBlank(message = "Estado de habitación no pueden estar en blanco")
    private String estado;
    @NotNull(message = "El precio de habitación no puede ser nula")
    private Float precio;

    @ManyToMany
    private List<Reserva> reserva = new ArrayList<>();
    
    /** The reserva habitacion. */
    @OneToMany(mappedBy = "habitacion")
    //@JsonManagedReference
    private List<DetalleReserva> reserva_habitacion = new ArrayList<>();
}