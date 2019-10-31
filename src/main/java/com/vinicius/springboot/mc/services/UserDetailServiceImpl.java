package com.vinicius.springboot.mc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vinicius.springboot.mc.model.Cliente;
import com.vinicius.springboot.mc.repositories.ClienteRepository;
import com.vinicius.springboot.mc.security.UserSpringSecurity;

/**
 * Classe responsável pela implementação do user detail service.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private ClienteRepository repository;
	
	/**
	 * Metodo que busca um usuario pelo ID
	 * @return detalhes do cliente - Cliente - id, email, senha, Perfil perfis.
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Cliente cliente = repository.findByEmail(email);
		
		if (cliente == null) {
			throw new UsernameNotFoundException(email);
		}
		
		return new UserSpringSecurity(cliente.getId(), cliente.getEmail(), cliente.getSenha(), cliente.getPerfis());
	}

}
