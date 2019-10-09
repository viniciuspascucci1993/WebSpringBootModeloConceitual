package com.vinicius.springboot.mc.dto;

import java.io.Serializable;

/**
 * Classe respon´savel por conter usuario e senha, credenciais do usuario.
 * @author Vinicius-PC - Vinicius Torres Pascucci
 */
public class CredenciaisDTO implements Serializable{

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Representa o usuario.
	 */
	private String usuario;
	
	/**
	 * Representa a senha.
	 */
	private String senha;
	
	/**
	 * Construtor vazio
	 */
	public CredenciaisDTO() {  }

	/**
	 * Metodo get()
	 * @return usuario - String - nome de usuario ou email.
	 */
	public String getUsuario() {
		return this.usuario;
	}

	/**
	 * Metodo set().
	 * @param usuario - String - nome de usuario ou email.
	 */
	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Metodo get().
	 * @return senha - String - senha do usuario.
	 */
	public String getSenha() {
		return this.senha;
	}

	/**
	 * Metodo set().
	 * @param senha - String - senha do usuario.
	 */
	public void setSenha(final String senha) {
		this.senha = senha;
	}
	
	
	
}
