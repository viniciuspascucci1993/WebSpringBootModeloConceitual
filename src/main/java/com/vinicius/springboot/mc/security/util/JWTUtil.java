package com.vinicius.springboot.mc.security.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Classe Utilitária para o JWT token.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Component
public class JWTUtil {

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiresToken;
	
	/**
	 * Metodo para gerar TOKEN.
	 * @param username - String - login.
	 * @return Jws.builder.
	 */
	// Metodo para gerar TOKEN.
	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + expiresToken))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}
	
	/**
	 * Metodo para verificar se o TOKEN é válido.
	 * @param token - String - token.
	 * @return true.
	 */
	// Testando se um token é válido
	public boolean tokenValido(String token) {
		
		Claims claims = getClaims(token);
		
		if (claims != null) {
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date dataAgora = new Date(System.currentTimeMillis());
			
			if (username != null && expirationDate != null && dataAgora.before(expirationDate)) {
				//token válido OK
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Metodo para recuperar um Claims a partir de um token
	 * @param token - String - token.
	 * @return secret.
	 */
	// Função que recupera os Claims a partir de um token
	private Claims getClaims(String token) {
		
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody(); 
		
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Metodo para capturar um usuario (getUsername)
	 * @param token - String - token.
	 * @return usuario.
	 */
	// Capiturando um usuario
	public String getUsername(String token) {
		
		Claims claims = getClaims(token);
		
		if (claims != null) {
			return claims.getSubject();
		}
		return null;
	}
	
}
