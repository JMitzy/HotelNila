package com.grupouno.hotelnila.dto;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidadorFecha  implements ConstraintValidator<ValidarFecha, ReservaDTO> {
	
	@Override
	public boolean isValid(ReservaDTO reserva, ConstraintValidatorContext context) {
	    if (reserva.getFechaInicio() == null || reserva.getFechaFin() == null) {
	        return true;
	    }
	    boolean esValido = reserva.getFechaInicio().before(reserva.getFechaFin());
	    if (!esValido) {
	        context.disableDefaultConstraintViolation();
	        context.buildConstraintViolationWithTemplate("La fecha de inicio debe ser anterior a la fecha de fin")
	               .addConstraintViolation();
	    }
	    return esValido;
	}
}
