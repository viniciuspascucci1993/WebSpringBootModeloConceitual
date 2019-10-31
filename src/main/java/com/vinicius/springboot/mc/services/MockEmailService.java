package com.vinicius.springboot.mc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

/**
 * Classe responsavel por enviar um email mock.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
public class MockEmailService extends AbstractEmailService{

	private static final Logger logger = LoggerFactory.getLogger(MockEmailService.class);
	
	/**
	 * Enviando e-mail Mock de exemplo para testes.
	 */
	@Override
	public void sendEmail(SimpleMailMessage mensagem) {
		
		logger.info("Simulando envio de email....");
		
		logger.info(mensagem.toString());
		
		logger.info("E-mail enviado...");
		
	}

}
