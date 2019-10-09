package com.vinicius.springboot.mc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vinicius.springboot.mc.model.Cliente;
import com.vinicius.springboot.mc.repositories.ClienteRepository;
import com.vinicius.springboot.mc.resources.util.GerarSenhaAleatoria;
import com.vinicius.springboot.mc.services.exception.ObjectNotFoundException;

/**
 * Classe responsável por implementar o "esqueci minha senha".
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Service
public class AuthService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoderPas;
	
	@Autowired
	private EmailService emailService;
	
	public void sendNewPassword(String email) {
		//verificar se o email existe
		 Cliente cliente = clienteRepository.findByEmail(email);
		 if (cliente == null) {
			throw new ObjectNotFoundException("E-mail não encontrado");
		}
		 
		String novaSenha = GerarSenhaAleatoria.generatePasswordNew();
		cliente.setSenha(encoderPas.encode(novaSenha));
		
		clienteRepository.save(cliente);
		
		emailService.sendNewPasswordEmail(cliente, novaSenha);
	}
}
