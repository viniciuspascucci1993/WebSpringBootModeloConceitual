package com.vinicius.springboot.mc.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.vinicius.springboot.mc.model.Cliente;
import com.vinicius.springboot.mc.model.Pedido;

/**
 * Classe abstrata para trabalhar com os padrões de strategy e template method.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
public abstract class AbstractEmailService implements EmailService{
	
	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private JavaMailSender javaMail;
	
	@Autowired
	private TemplateEngine templateEngine;

	/**
	 * Metodo para enviar uma confirmação de email.
	 */
	// Implementação do metodo para enviar e-mail
	@Override
	public void sendOrderConfirmationEmail( Pedido pedido) {
		
		SimpleMailMessage message = prepareSimpleMailMessageFromPedid(pedido);
		
		sendEmail(message);
	}

	/**
	 * Metodo para preparar um email a partir do Pedido.
	 * @param pedido - Pedido - pedido.
	 * @return SimpleMailMessage message.
	 */
	protected SimpleMailMessage prepareSimpleMailMessageFromPedid(Pedido pedido) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(pedido.getCliente().getEmail());
		message.setFrom(sender);
		message.setSubject("Pedido realizado com sucesso! - Codigo do pedido: " + pedido.getIdPedido());
		message.setSentDate(new Date(System.currentTimeMillis()));
		message.setText(pedido.toString());
		
		return message;
	}
	
	/**
	 * Metodo que envia a nova senha por email.
	 */
	@Override
	public void sendNewPasswordEmail(Cliente cliente, String newPas) {
		
		SimpleMailMessage message = prepareNewPasswordEmail(cliente, newPas);
		
		sendEmail(message);
	}

	/**
	 * Metodo que preparar a criação da nova senha e envia para o e-mail do usuario.
	 * @param cliente - Cliente - cliente.
	 * @param newPass - String - nova senha.
	 * @return SimpleMailMessage message.
	 */
	protected SimpleMailMessage prepareNewPasswordEmail(Cliente cliente, String newPass) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(cliente.getEmail());
		message.setFrom(sender);
		message.setSubject("Solicitação de nova Senha: ");
		message.setSentDate(new Date(System.currentTimeMillis()));
		message.setText("Nova Senha: " + newPass);
		
		return message;
	}
	
	/**
	 * Metodo responsável por buscar um Contexto para exibir o template em HTML para envio de Pedido.
	 * @param obj - Pedido - Object.
	 * @return templateEngine
	 */
	protected String htmlFromTemplatePedido(Pedido obj) {
		
		Context contexto = new Context();
		
		contexto.setVariable("pedido", obj);
		
		// Processar o template para me retornar o HTML na forma de String.
		return templateEngine.process("email/confirmacaoPedido", contexto);
		
	}
	
	/**
	 * Enviar email no formato HTML.
	 */
	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido obj) {
		
		MimeMessage mimeMessage;
		try {
			mimeMessage = prepareMimeMessageFromPedid(obj);
			sendHtmlEmail(mimeMessage);
		} catch (MessagingException e) {
			sendOrderConfirmationEmail(obj); 
		}
	}	
	
	
	/**
	 * Metodo para enviar e-mail no formato de HTML.
	 * @param obj - Pedido - Object.
	 * @return mimemessage
	 * @throws MessagingException
	 */
	protected MimeMessage prepareMimeMessageFromPedid(Pedido obj) throws MessagingException {
		
		MimeMessage mimeMessage = javaMail.createMimeMessage();
		
		MimeMessageHelper mimeMessageHelpewr = new MimeMessageHelper(mimeMessage, true);
		
		mimeMessageHelpewr.setTo(obj.getCliente().getEmail());
		
		mimeMessageHelpewr.setFrom(sender);
		
		mimeMessageHelpewr.setSubject("Pedido Realizado com sucesso! Codigo do pedido: " + obj.getIdPedido());
		
		mimeMessageHelpewr.setSentDate(new Date(System.currentTimeMillis()));
		
		mimeMessageHelpewr.setText(htmlFromTemplatePedido(obj), true);
		
		return mimeMessage;
	}
}
