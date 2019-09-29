package com.vinicius.springboot.mc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.vinicius.springboot.mc.model.Cliente;
import com.vinicius.springboot.mc.services.validations.ValidationClienteUpdate;

/**
 * Classe responsável por transportar apenas os atributos necessarios para nossas operações.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@ValidationClienteUpdate
public class ClienteDTO implements Serializable {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Representa o identificador do cliente. 
	 */
	private Integer id;
	
	/**
	 * Representa o nome do cliente
	 */
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 80, message = "O Tamanho deve ser entre 5 e 80 caracteres")
	private String nomeCliente;
	
	/**
	 * Representa o e-mail do cliente.
	 */
	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "E-mail inválido")
	private String email;
	
	/**
	 * Construtor vazio.
	 */
	public ClienteDTO() { }

	/**
	 * Construtor passando um Object Categoria.
	 * @param clienteObj.
	 */
	public ClienteDTO( Cliente clienteObj ) {
		id = clienteObj.getId();
		nomeCliente = clienteObj.getNomeCliente();
		email = clienteObj.getEmail();
	}

	/**
	 * Metodo get().
	 * @return id - Integer - identificador cliente.
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Metodo set().
	 * @param id -  - Integer - identificador cliente.
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * Metodo get().
	 * @return nomeCliente - String - nome do cliente.
	 */
	public String getNomeCliente() {
		return this.nomeCliente;
	}

	/**
	 * Metodo set().
	 * @param nomeCliente - String - nome do cliente.
	 */
	public void setNomeCliente(final String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	/**
	 * Metodo get().
	 * @return email - String - e-mail do cliente.
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Metodo set().
	 * @param email - String - e-mail do cliente.
	 */
	public void setEmail(final String email) {
		this.email = email;
	} 
}
