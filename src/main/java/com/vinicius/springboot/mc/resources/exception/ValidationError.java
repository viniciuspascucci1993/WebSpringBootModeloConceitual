package com.vinicius.springboot.mc.resources.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por validation errors mostrando uma lista de erros.
 * @author Vinicius-PC
 *
 */
public class ValidationError extends StandardError {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Representa uma lista de erros.
	 */
	private List<FieldMessage> errors = new ArrayList<FieldMessage>();
	
	public ValidationError(Integer statusHttp, String message, Long timeStamp) {
		super(statusHttp, message, timeStamp);
		
	}

	/**
	 * Metodo get().
	 * @return errors - List<FieldMessage> - lista de erros.
	 */
	public List<FieldMessage> getErrors() {
		return this.errors;
	}

	/**
	 * Metodo addError().
	 * Adiciona os erros a nossa lista.
	 * @param errors - List<FieldMessage> - lista de erros.
	 */
	public void addError(final String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}

}
