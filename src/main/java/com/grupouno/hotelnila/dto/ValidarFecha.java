package com.grupouno.hotelnila.dto;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.grupouno.hotelnila.util.ApiResponse;

@Constraint(validatedBy = ValidadorFecha.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidarFecha {
    String message() default "La fecha de inicio debe ser anterior a la fecha de fin";
   
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
