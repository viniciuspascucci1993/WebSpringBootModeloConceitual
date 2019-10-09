package com.vinicius.springboot.mc.security.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
	
	
	// Metodo para gerar TOKEN.
	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + expiresToken))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}
	
}
