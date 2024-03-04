package com.grupouno.hotelnila.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHabitacion;
    private String nroHabitacion;
    private String tipoHabitacion;
    private String estado;
    private Float precio;

    @ManyToMany(mappedBy = "habitaciones")
    @JsonManagedReference
    @JsonIgnore
    private List<Reserva> reserva = new ArrayList<>();
}
