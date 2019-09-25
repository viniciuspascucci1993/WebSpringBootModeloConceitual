package com.vinicius.springboot.mc.services.exception;

/**
 * Classe responsável por tratar as nossas exceções personalizadas.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 *
 */
public class ObjectNotFoundException extends RuntimeException{

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * ObjectNotFoundException.
	 * @param mensagem - String - mensagem personalizada.
	 */
	public ObjectNotFoundException(String mensagem) { 
		super(mensagem);
	}
	
	/**
	 * ObjectNotFoundException.
	 * @param mensagem - String - mensagem personalizada.
	 * @param cause - Throwable - causa da exceção.
	 */
	public ObjectNotFoundException(String mensagem, Throwable cause) {
		super(mensagem, cause);
	}

}
