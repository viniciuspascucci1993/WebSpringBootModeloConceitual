package com.vinicius.springboot.mc.resources.exception;

import java.io.Serializable;

/**
 * Classe auxiliar para nos ajudar no handler.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
public class FieldMessage implements Serializable {
	
	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Representa o nome do campo.
	 */
	private String fieldName;
	
	/**
	 * Representa a mensagem exibida.
	 */
	private String message;
	
	/**
	 * Construtor vazio.
	 */
	public FieldMessage() { }

	/**
	 * Construtor com argumentos.
	 * @param fieldName - String - nome do campo.
	 * @param message - String - mensagem a ser exibida.
	 */
	public FieldMessage(String fieldName, String message) {
		super();
		this.fieldName = fieldName;
		this.message = message;
	}

	/**
	 * Metodo get().
	 * @return fieldName - String - nome do campo.
	 */
	public String getFieldName() {
		return this.fieldName;
	}

	/**
	 * Metodo set().
	 * @param fieldName - String - nome do campo.
	 */
	public void setFieldName(final String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * Metodo get().
	 * @return message - String - mensagem exibida.
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * Metodo set().
	 * @param message - String - mensagem exibida.
	 */
	public void setMessage(final String message) {
		this.message = message;
	}
}
