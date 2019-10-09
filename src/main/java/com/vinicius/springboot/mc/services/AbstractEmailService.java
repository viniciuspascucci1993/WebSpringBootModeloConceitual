package com.vinicius.springboot.mc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.vinicius.springboot.mc.model.Cliente;
import com.vinicius.springboot.mc.model.Pedido;

/**
 * Classe abstrata para trabalhar com os padrões de strategy e template method.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
public abstract class AbstractEmailService implements EmailService{
	
	@Value("${default.sender}")
	private String sender;

	// Implementação do metodo para enviar e-mail
	@Override
	public void sendOrderConfirmationEmail( Pedido pedido) {
		
		SimpleMailMessage message = prepareSimpleMailMessageFromPedid(pedido);
		
		sendEmail(message);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedid(Pedido pedido) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(pedido.getCliente().getEmail());
		message.setFrom(sender);
		message.setSubject("Pedido realizado com sucesso! - Codigo do pedido: " + pedido.getIdPedido());
		message.setSentDate(new Date(System.currentTimeMillis()));
		message.setText(pedido.toString());
		
		return message;
	}
	
	@Override
	public void sendNewPasswordEmail(Cliente cliente, String newPas) {
		
		SimpleMailMessage message = prepareNewPasswordEmail(cliente, newPas);
		
		sendEmail(message);
	}

	protected SimpleMailMessage prepareNewPasswordEmail(Cliente cliente, String newPass) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(cliente.getEmail());
		message.setFrom(sender);
		message.setSubject("Solicitação de nova Senha: ");
		message.setSentDate(new Date(System.currentTimeMillis()));
		message.setText("Nova Senha: " + newPass);
		
		return message;
	}
}
