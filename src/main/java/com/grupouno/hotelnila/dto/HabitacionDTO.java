package com.grupouno.hotelnila.dto;

import java.util.ArrayList;
import java.util.List;

import com.grupouno.hotelnila.domain.Reserva;

import lombok.Data;


@Data
public class HabitacionDTO {
	private Long idHabitacion;
    private String nroHabitacion;
    private String tipoHabitacion;
    private String estado;
    private Float precio;
    private List<Reserva> reserva = new ArrayList<>();
}
