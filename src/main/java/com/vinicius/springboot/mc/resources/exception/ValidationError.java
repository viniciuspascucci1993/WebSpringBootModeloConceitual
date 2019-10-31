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
	
	/**
	 * Construtor com argumentos.
	 * @param timestamp - Long - timestamp.
	 * @param statusHttp - Integer - status.
	 * @param error - String - error.
	 * @param message - String - message.
	 * @param path - String - path.
	 */
	public ValidationError(Long timestamp, Integer statusHttp, String error, String message, String path) {
		super(timestamp, statusHttp, error, message, path);
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
