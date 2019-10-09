package com.vinicius.springboot.mc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * Classe responsável por conter os atributos necessarios para o "esqueci minha senha"
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
public class AuxEmailDTO implements Serializable {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Representa o email
	 */
	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "E-mail inválido")
	private String email;
	
	/**
	 * Contrutor vazio
	 */
	public AuxEmailDTO() {  }

	/**
	 * Metodo get().
	 * @return email - String - e-mail.
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Metodo set().
	 * @param email - String - e-mail.
	 */
	public void setEmail(final String email) {
		this.email = email;
	}
}
