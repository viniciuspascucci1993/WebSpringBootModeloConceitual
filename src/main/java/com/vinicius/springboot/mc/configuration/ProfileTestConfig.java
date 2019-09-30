package com.vinicius.springboot.mc.configuration;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.vinicius.springboot.mc.services.DatabaseService;

/**
 * Classe de configuração indicando que esse perfil é de teste da nossa aplicação.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Configuration
@Profile("test")
public class ProfileTestConfig {

	@Autowired
	private DatabaseService dbService;
	
	/**
	 * Método que será responsável por instanciar o meu Database no profile de teste.
	 * @return true.
	 * @throws ParseException 
	 */
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		
		dbService.instantiateTestDatabase();
		
		return true;
	}
}
