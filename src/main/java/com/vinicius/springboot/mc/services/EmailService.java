package com.vinicius.springboot.mc.services;

import org.springframework.mail.SimpleMailMessage;

import com.vinicius.springboot.mc.model.Pedido;

/**
* Service responsável por enviar um e-mail com os padrões strategy e template method.
* @author Vinicius-PC - Vinicius Torres Pascucci.
*/
public interface EmailService {
	
	void sendOrderConfirmationEmail( Pedido pedido);
	
	void sendEmail( SimpleMailMessage mensagem);

}
