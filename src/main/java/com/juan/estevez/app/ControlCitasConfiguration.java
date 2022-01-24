package com.juan.estevez.app;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configura el objeto para mapear los DTO (ModelMapper)
 * 
 * @author Juan Carlos Estevez Vargas
 */
@Configuration
public class ControlCitasConfiguration {

	/**
	 * Se encarga de construir una instancia del objeto ModelMapper
	 * 
	 * @return Una nueva instancia del objeto ModelMapper
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
