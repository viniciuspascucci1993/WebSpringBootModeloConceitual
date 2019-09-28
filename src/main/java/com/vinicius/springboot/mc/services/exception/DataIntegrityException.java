package com.vinicius.springboot.mc.services.exception;

/**
 * Classe responsável por tratar as nossas exceções personalizadas.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 *
 */
public class DataIntegrityException extends RuntimeException{

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * DataIntegrityException.
	 * @param mensagem - String - mensagem personalizada.
	 */
	public DataIntegrityException(String mensagem) { 
		super(mensagem);
	}
	
	/**
	 * DataIntegrityException.
	 * @param mensagem - String - mensagem personalizada.
	 * @param cause - Throwable - causa da exceção.
	 */
	public DataIntegrityException(String mensagem, Throwable cause) {
		super(mensagem, cause);
	}

}
