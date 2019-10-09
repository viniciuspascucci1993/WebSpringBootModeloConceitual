 package com.vinicius.springboot.mc.services.exception;

/**
 * Classe responsável por tratar as nossas exceções personalizadas.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 *
 */
public class AuthorizationException extends RuntimeException{

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * AuthorizationException.
	 * @param mensagem - String - mensagem personalizada.
	 */
	public AuthorizationException(String mensagem) { 
		super(mensagem);
	}
	
	/**
	 * AuthorizationException.
	 * @param mensagem - String - mensagem personalizada.
	 * @param cause - Throwable - causa da exceção.
	 */
	public AuthorizationException(String mensagem, Throwable cause) {
		super(mensagem, cause);
	}

}
