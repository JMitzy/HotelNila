package com.grupouno.hotelnila.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.grupouno.hotelnila.domain.Cliente;
import com.grupouno.hotelnila.domain.Habitacion;
import com.grupouno.hotelnila.domain.Recepcionista;

import lombok.Data;

@Data
public class ReservaDTO {
	private Long idReserva;
	private Date fechaInicio;
	private Date fechaFin;
	private List<Habitacion> habitaciones = new ArrayList<>();
	private Recepcionista recepcionista;
	private Cliente cliente;
}
