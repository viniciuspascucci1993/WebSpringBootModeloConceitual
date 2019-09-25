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
	 * Representa o status HTTP.
	 */
	private Integer statusHttp;
	
	/**
	 * Representa a mensagem.
	 */
	private String message;
	
	/**
	 * Representa o momento do erro.
	 */
	private Long timeStamp;

	/**
	 * Construtor com argumentos.
	 * @param statusHttp - Integer - status code.
	 * @param message - String - mensagem do erro.
	 * @param timeStamp - Long - momento do erro.
	 */
	public StandardError(Integer statusHttp, String message, Long timeStamp) {
		super();
		this.statusHttp = statusHttp;
		this.message = message;
		this.timeStamp = timeStamp;
	}

	/**
	 * Metodo get().
	 * @return statusHttp - Integer - status http.
	 */
	public Integer getStatusHttp() {
		return this.statusHttp;
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
	 * @return timeStamp - Long - momento do erro.
	 */
	public Long getTimeStamp() {
		return this.timeStamp;
	}

	/**
	 * Metodo set().
	 * @param timeStamp - Long - momento do erro.
	 */
	public void setTimeStamp(final Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	

}
