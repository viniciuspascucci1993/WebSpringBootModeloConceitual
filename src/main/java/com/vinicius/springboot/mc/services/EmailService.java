package com.vinicius.springboot.mc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.vinicius.springboot.mc.model.Cliente;
import com.vinicius.springboot.mc.model.Pedido;

/**
* Service responsável por conter os metodos que enviam email, novo password e ordem de confirmação de pedido.
* @author Vinicius-PC - Vinicius Torres Pascucci.
*/
public interface EmailService {
	
	// Enviar ordem de confirmação de pedido
	void sendOrderConfirmationEmail( Pedido pedido);
	
	// Enviar e-mail
	void sendEmail( SimpleMailMessage mensagem);
	
	// Enviar novo password por email.
	void sendNewPasswordEmail(Cliente cliente, String email);
	
	// Enviar email formato HTML
	void sendOrderConfirmationHtmlEmail(Pedido obj); 
	
	// Enviar e-mail HTML
	void sendHtmlEmail(MimeMessage msg); 

}
