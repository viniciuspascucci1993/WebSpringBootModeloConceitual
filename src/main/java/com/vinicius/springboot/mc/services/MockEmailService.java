package com.vinicius.springboot.mc.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

/**
 * Classe responsavel por enviar um email mock.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
public class MockEmailService extends AbstractEmailService{

	private static final Logger logger = LoggerFactory.getLogger(MockEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage mensagem) {
		
		logger.info("Simulando envio de email....");
		
		logger.info(mensagem.toString());
		
		logger.info("E-mail enviado...");
		
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		
		logger.info("Simulando envio de email HTML....");
		
		logger.info(msg.toString());
		
		logger.info("E-mail enviado...");
		
		
	}

}
