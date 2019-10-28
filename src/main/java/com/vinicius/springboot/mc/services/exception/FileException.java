 package com.vinicius.springboot.mc.services.exception;

/**
 * Classe responsável por tratar as nossas exceções personalizadas de imagem.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
public class FileException extends RuntimeException{

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * FileException.
	 * @param mensagem - String - mensagem personalizada.
	 */
	public FileException(String mensagem) { 
		super(mensagem);
	}
	
	/**
	 * FileException.
	 * @param mensagem - String - mensagem personalizada.
	 * @param cause - Throwable - causa da exceção.
	 */
	public FileException(String mensagem, Throwable cause) {
		super(mensagem, cause);
	}

}
