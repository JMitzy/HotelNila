package com.grupouno.hotelnila.domain;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
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
    private String nroHabitacion;
    private String tipoHabitacion;
    private String estado;
    private Float precio;

    @ManyToMany
    private List<Reserva> reserva = new ArrayList<>();
}
