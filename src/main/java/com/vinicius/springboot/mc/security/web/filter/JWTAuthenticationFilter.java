package com.vinicius.springboot.mc.security.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinicius.springboot.mc.dto.CredenciaisDTO;
import com.vinicius.springboot.mc.security.UserSpringSecurity;
import com.vinicius.springboot.mc.security.util.JWTUtil;

/**
 * Classe responsável por filtrar nossas requisições de autenticação com JWT Token.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	
	private AuthenticationManager authenticationManager;
	
	private JWTUtil jwtUtil;
	
	/**
	 * Construtor com argumentos.
	 * @param authenticationManager - AuthenticationManager - autentication.
	 * @param jwtUtil - JwtUtil - Classe Utilitária.
	 */
	public JWTAuthenticationFilter( AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}
	
	/**
	 * Metodo que consiste em uma tentativa de autenticação por meio de um login e senha do cliente.
	 * @return Authentication auth;
	 */
	@Override
	 public Authentication attemptAuthentication( HttpServletRequest req, HttpServletResponse res ) throws AuthenticationException {
		 
		
		try {
			CredenciaisDTO credenciais = new ObjectMapper()
					.readValue(req.getInputStream(), CredenciaisDTO.class);
			
			UsernamePasswordAuthenticationToken tokenAuth = new UsernamePasswordAuthenticationToken(credenciais.getUsuario(), credenciais.getSenha(), new ArrayList<>());
			
			Authentication auth = authenticationManager.authenticate(tokenAuth);
			
			return auth;
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	 }
	
	/**
	 * Metodo para caso a autenticação seja bem sucedida.
	 */
	@Override
	protected void successfulAuthentication( HttpServletRequest req, 
											 HttpServletResponse res, 
											 FilterChain chain, 
											 Authentication auth ) throws IOException, ServletException {
		
		String username = ((UserSpringSecurity) auth.getPrincipal()).getUsername();
		
		String token = jwtUtil.generateToken(username);
		
		res.addHeader("Authorization", "Bearer " + token);
		
		res.addHeader("access-control-expose-headers", "Authorization");
		
	}
	
	/**
	 * Metodo para caso aconteça alguma falha de autenticação no momento do login.
	 * @author Vinicius-PC - Vinicius Torres Pascucci.
	 */
	@SuppressWarnings("unused")
	private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {
		 
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
                throws IOException, ServletException {
            response.setStatus(401);
            response.setContentType("application/json"); 
            response.getWriter().append(json());
        }
        
        /**
         * Metodo private indicando a saida JSON caos a resposta da requisição falha e retorne um status code 401: Não Autorizado.
         * @return JSON.
         */
        private String json() {
            long date = new Date().getTime();
            return "{\"timestamp\": " + date + ", "
                + "\"status\": 401, "
                + "\"error\": \"Não autorizado\", "
                + "\"message\": \"Email ou senha inválidos\", "
                + "\"path\": \"/login\"}";
        }
    }
}
