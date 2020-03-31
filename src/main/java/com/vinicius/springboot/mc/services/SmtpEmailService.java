package com.vinicius.springboot.mc.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * Classe que sera responsavel por enviar um e-mail real para a google.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
public class SmtpEmailService extends AbstractEmailService {
	
	private static final Logger logger = LoggerFactory.getLogger(SmtpEmailService.class);

	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private JavaMailSender javaMail;	
	
	/**
	 * Simulando envio de email SMTP com o servidor da Google
	 */
	@Override
	public void sendEmail(SimpleMailMessage mensagem) {
		
		logger.info("Enviando e-mail....");
		
		mailSender.send(mensagem);
		
		logger.info("Email enviado");
		
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		
		logger.info("Enviando e-mail....");
		
		javaMail.send(msg);
		
		logger.info("Email enviado");
	}

}
