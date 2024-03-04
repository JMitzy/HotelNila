package com.grupouno.hotelnila.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReserva;
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "reserva_habitacion",
            joinColumns = @JoinColumn(name = "idReserva"),
            inverseJoinColumns = @JoinColumn(name = "idHabitacion")
    )
    @JsonIgnoreProperties("reserva")
    private List<Habitacion> habitaciones = new ArrayList<>();
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("reserva")
    private Recepcionista recepcionista;

    
    //private Cliente cliente;

}
