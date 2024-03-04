package com.grupouno.hotelnila.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idReserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReserva;
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
   @ManyToMany
    @JoinTable(
	  name = "reserva_habitacion", 
	  joinColumns = @JoinColumn(name = "idReserva"), 
	  inverseJoinColumns = @JoinColumn(name = "idHabitacion"))
	  private List<Habitacion> habitaciones =new ArrayList<>();
   
    @ManyToOne
    //@JsonBackReference
    private Recepcionista recepcionista;

    @ManyToOne
    //@JsonBackReference
    private Cliente cliente;
    
    @OneToOne
    //@JsonManagedReference
    private Factura factura;

}
