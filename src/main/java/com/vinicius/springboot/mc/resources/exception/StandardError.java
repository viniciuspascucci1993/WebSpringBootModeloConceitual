package com.vinicius.springboot.mc.resources.exception;

import java.io.Serializable;

/**
 * Classe auxiliar para nos ajudar no handler.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
public class StandardError implements Serializable {
	
	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Representa o momento do erro.
	 */
	private Long timestamp;
	
	/**
	 * Representa o status HTTP.
	 */
	private Integer statusHttp;
	
	/**
	 * Representa o erro.
	 */
	private String error;
	
	/**
	 * Representa a mensagem.
	 */
	private String message;
	
	/**
	 * Representa o caminho do erro.
	 */
	private String path;
	


	/**
	 * Construtor com argumentos.
	 * @param statusHttp - Integer - status code.
	 * @param message - String - mensagem do erro.
	 * @param timeStamp - Long - momento do erro.
	 */
	public StandardError(Long timestamp, Integer statusHttp, String error, String message, String path) {
		super();
		this.timestamp = timestamp;
		this.statusHttp = statusHttp;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	/**
	 * Metodo get().
	 * @return timeStamp - Long - momento do erro.
	 */
	public Long getTimeStamp() {
		return this.timestamp;
	}

	/**
	 * Metodo set().
	 * @param timeStamp - Long - momento do erro.
	 */
	public void setTimeStamp(final Long timestamp) {
		this.timestamp = timestamp;
	}
	
	/**
	 * Metodo get().
	 * @return statusHttp - Integer - status http.
	 */
	public Integer getStatusHttp() {
		return this.statusHttp;
	}

	/**
	 * Metodo get().
	 * @return error - String - erro.
	 */
	public String getError() {
		return this.error;
	}

	/**
	 * Metodo set().
	 * @param error - String - erro.
	 */
	public void setError(final String error) {
		this.error = error;
	}

	/**
	 * Metodo set().
	 * @param statusHttp - Integer - status http.
	 */
	public void setStatusHttp(final Integer statusHttp) {
		this.statusHttp = statusHttp;
	}

	/**
	 * Metodo get(),
	 * @return messagem - String - mensagem do erro.
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * Metodo set()
	 * @param message - String - mensagem do erro.
	 */
	public void setMessage(final String message) {
		this.message = message;
	}

	/**
	 * Metodo get().
	 * @return path - String - caminho do erro.
	 */
	public String getPath() {
		return this.path;
	}

	/**
	 * Metodo set().
	 * @param path - String - caminho do erro.
	 */
	public void setPath(final String path) {
		this.path = path;
	}

	

}
