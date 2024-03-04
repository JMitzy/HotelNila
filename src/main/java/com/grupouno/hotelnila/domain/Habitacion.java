package com.grupouno.hotelnila.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
@Data
@Entity
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHabitacion;
    private String tipoHabitacion;
    
    private String estado;
    private Float precio;

    @ManyToMany(mappedBy = "habitaciones")
    @JsonManagedReference
    @JsonIgnore
    private Set<Reserva> reserva = new HashSet<>();
}
