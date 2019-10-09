package com.vinicius.springboot.mc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.vinicius.springboot.mc.security.UserSpringSecurity;

/**
 * Classe responsável por pegar o usuario logado.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
public class UserService {
	
	// Pega o usuario logado.
	public static UserSpringSecurity getUserLogado() {
		
		//Obter usuario logado
		try {
			return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		} catch( Exception e ) {
			return null;
		}
	}

}
