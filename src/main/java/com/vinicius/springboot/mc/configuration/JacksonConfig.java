package com.vinicius.springboot.mc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinicius.springboot.mc.model.PagamentoComBoleto;
import com.vinicius.springboot.mc.model.PagamentoComCartao;

/**
 * Classe de configuração para registrar as nossas superclasses.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Configuration
public class JacksonConfig {

	 // https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-ofinterfaceclass-without-hinting-the-pare 
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				
				objectMapper.registerSubtypes(PagamentoComBoleto.class);
				objectMapper.registerSubtypes(PagamentoComCartao.class);
				super.configure(objectMapper);
			}
		};
		return builder;
	}
}
