/*
 * @file ApplicationConfig.java;
 * @Autor (c)2024 JhenniferMendoza
 * @Created 8 mar. 2024,16:58:14
 */
package com.grupouno.hotelnila.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Clase de configuración de la aplicación que define los beans necesarios
 */
@Configuration
public class ApplicationConfig {
	
	/**
	 *Define un bean de ModelMapper que se puede utilizar para mapear objetos 
	 *entre diferentes tipos.
	 *
	 * @return Un objeto ModelMapper configurado.
	 */
	@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
