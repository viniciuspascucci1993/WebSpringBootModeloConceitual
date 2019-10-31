package com.vinicius.springboot.mc.configuration;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.vinicius.springboot.mc.services.DatabaseService;
import com.vinicius.springboot.mc.services.EmailService;
import com.vinicius.springboot.mc.services.SmtpEmailService;

/**
 * Classe de configuração indicando que esse perfil é de desenvolvimento da nossa aplicação.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Configuration
@Profile("dev")
public class ProfileDevConfig {

	@Autowired
	private DatabaseService dbService;
	
	// Captura o valor da chave no arquivo .properties.
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String getProperty;
	
	/**
	 * Método que será responsável por instanciar o meu Database no profile de teste.
	 * @return true.
	 * @throws ParseException 
	 */
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		
		if (!"create".equals(getProperty) ) {
			return false;
		}
		
		dbService.instantiateTestDatabase();
		
		return true;
	}
	
	/**
	 * Bean de configuração para simular envio de email SMTP com o servidor da Google.
	 * @return SmtpEmailService().
	 */
	@Bean
	public EmailService enviarEmailSmtp() {
		return new SmtpEmailService();
	}
}
