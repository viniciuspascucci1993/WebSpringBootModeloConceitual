package com.vinicius.springboot.mc.dto;

import java.io.Serializable;

/**
 * Classe responsável por mandar para a requisição apenas os atributos dos quais precisamos.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
public class ClienteNewDTO implements Serializable {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Representa o nome do cliente
	 */
	private String nomeCliente;
	
	/**
	 * Representa o e-mail do cliente.
	 */
	private String email;
	
	/**
	 * Representa o CPF e/ou CNPJ do cliente
	 */
	private String cpfCnpj;
	
	/**
	 * Representa o tipo enumerado do cliente.
	 */
	private Integer tipoCliente;	
	
	/**
	 * Representa o logradouro do endereço.
	 */
	private String logradouro;
	
	/**
	 * Representa o número do endereço.
	 */
	private String numero;
	
	/**
	 * Representa o complemento do endereço.
	 */
	private String complemento;
	
	/**
	 * Representa o bairro do endereço.
	 */
	private String bairro;
	
	/**
	 * Representa o CEP do endereço.
	 */
	private String cep;
	
	/**
	 * Representa o telefone residencial.
	 */
	private String telefoneResidencial;
	
	/**
	 * Representa o telefone comercial.
	 */
	private String telefoneComecial;
	
	/**
	 * Representa o celular.
	 */
	private String celular;
	
	/**
	 * Representa o id da cidade.
	 */
	private Integer cidadeId;
	
	public ClienteNewDTO() { }

	public String getNomeCliente() {
		return this.nomeCliente;
	}

	/**
	 * Metodo get().
	 * @return nomeCliente - String - nome do cliente.
	 */
	public void setNomeCliente(final String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	/**
	 * Metodo set().
	 * @param nomeCliente - String - nome do cliente.
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Metodo get().
	 * @return email - String - e-mail do cliente.
	 */
	public void setEmail(final String email) {
		this.email = email;
	}

	/**
	 * Metodo set().
	 * @param email - String - e-mail do cliente.
	 */
	public String getCpfCnpj() {
		return this.cpfCnpj;
	}

	/**
	 * Metodo get().
	 * @return cpfCnpj - String - CPF e/ou CNPJ do cliente.
	 */
	public void setCpfCnpj(final String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	/**
	 * Metodo get().
	 * @return tipoCliente - Object - tipo do cliente.
	 */
	public Integer getTipoCliente() {
		return this.tipoCliente;
	}

	/**
	 * Metodo set().
	 * @param tipoCliente - Object - tipo do cliente.
	 */
	public void setTipoCliente(final Integer tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	/**
	 * Metodo get().
	 * @return logradouro - String - logradouro do endereço.
	 */
	public String getLogradouro() {
		return this.logradouro;
	}

	/**
	 * Metodo set().
	 * @param logradouro - String - logradouro do endereço.
	 */
	public void setLogradouro(final String logradouro) {
		this.logradouro = logradouro;
	}

	/**
	 * Metodo get().
	 * @return numero - String - numero do endereço.
	 */
	public String getNumero() {
		return this.numero;
	}

	/**
	 * Metodo set().
	 * @param numero - String - numero do endereço.
	 */
	public void setNumero(final String numero) {
		this.numero = numero;
	}

	/**
	 * Metodo get().
	 * @return complemento - String - complemento do endereço.
	 */
	public String getComplemento() {
		return this.complemento;
	}

	/**
	 * Metodo set().
	 * @param complemento - String - complemento do endereço.
	 */
	public void setComplemento(final String complemento) {
		this.complemento = complemento;
	}

	/**
	 * Metodo get().
	 * @return bairro - String - bairro do endereço.
	 */
	public String getBairro() {
		return this.bairro;
	}

	/**
	 * Metodo set().
	 * @param bairro - String - bairro do endereço.
	 */
	public void setBairro(final String bairro) {
		this.bairro = bairro;
	}

	/**
	 * Metodo get().
	 * @return cep - String - cep do endereço.
	 */
	public String getCep() {
		return this.cep;
	}

	/**
	 * Metodo set().
	 * @param cep - String - cep do endereço.
	 */
	public void setCep(final String cep) {
		this.cep = cep;
	}

	/**
	 * Metodo get().
	 * @return telefoneResidencial - String - telefone residencial.
	 */
	public String getTelefoneResidencial() {
		return this.telefoneResidencial;
	}

	/**
	 * Metodo set().
	 * @param telefoneResidencial - String - telefone residencial.
	 */
	public void setTelefoneResidencial(final String telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}

	/**
	 * Metodo get().
	 * @return telefoneComercial - String - telefone comercial. 
	 */
	public String getTelefoneComecial() {
		return this.telefoneComecial;
	}

	/**
	 * Metodo set().
	 * @param telefoneComecial - String - telefone comercial.
	 */
	public void setTelefoneComecial(final String telefoneComecial) {
		this.telefoneComecial = telefoneComecial;
	}

	/**
	 * Metodo get().
	 * @return celular - String - celular.
	 */
	public String getCelular() {
		return this.celular;
	}

	/**
	 * Metodo set().
	 * @param celular - String - celular.
	 */
	public void setCelular(final String celular) {
		this.celular = celular;
	}

	/**
	 * Metodo get().
	 * @return cidadeId - Integer - identificador da cidade.
	 */
	public Integer getCidadeId() {
		return this.cidadeId;
	}

	/**
	 * Metodo set().
	 * @param cidadeId - Integer - identificador da cidade.
	 */
	public void setCidadeId(final Integer cidadeId) {
		this.cidadeId = cidadeId;
	}

}
