package com.vinicius.springboot.mc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * Classe que sera responsavel por enviar um e-mail real para a google.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
public class SmtpEmailService extends AbstractEmailService{
	
	private static final Logger logger = LoggerFactory.getLogger(SmtpEmailService.class);

	@Autowired
	private MailSender mailSender;
	
	/**
	 * Simulando envio de email SMTP com o servidor da Google
	 */
	@Override
	public void sendEmail(SimpleMailMessage mensagem) {
		
		logger.info("Enviando e-mail....");
		
		mailSender.send(mensagem);
		
		logger.info("Email enviado");
		
	}

}
