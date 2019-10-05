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
	
	protected String htmlFromTemplatePedido(Pedido obj) {
		
		Context contexto = new Context();
		
		contexto.setVariable("pedido", obj);
		
		return templateEngine.process("email/confirmacaoPedido", contexto);
		
	}
	
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
