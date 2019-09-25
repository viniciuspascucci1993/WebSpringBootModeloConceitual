package com.vinicius.springboot.mc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Classe responsável por identificar os atributos de um endereço.
 * @author Vinicius-PC - Vinicius Torres Pascucci,
 */
@Entity
public class Endereco implements Serializable{

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Representa o identificador do endereço.
	 */
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Integer id;
	
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
	 * Representa UMA cidade ou varias Cidades.
	 */
	@ManyToOne
	@JoinColumn( name = "cidade_id")	
	private Cidade cidade;
	/**
	 * Representa UM Cliente.
	 */
	@ManyToOne
	@JoinColumn( name = "cliente_id")
	private Cliente cliente;
	
	/**
	 * Contrutor vazio.
	 */
	public Endereco() { }

	/**
	 * Consturtor com argumentos.
	 * @param id - Integer - id endereço.
	 * @param logradouro - String - logradouro endereço.
	 * @param numero - String - numero endereço.
	 * @param complemento - String - complemento endereço.
	 * @param bairro - String - bairro endereço.
	 * @param cep - String - cep endereço.
	 * @param cidade - Object - cidade.
	 * @param cliente - Object - cliente
	 */
	public Endereco(Integer id, String logradouro, String numero, String complemento, String bairro, String cep, Cliente cliente, Cidade cidade) {
		super();
		this.id = id;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.cliente = cliente;
	}

	/**
	 * Metodo get().
	 * @return id - Integer - id endereço.
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Metodo set().
	 * @param id - Integer - id endereço. 
	 */
	public void setId(final Integer id) {
		this.id = id;
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
	 * Metodo get()
	 * @return cidade - Object - cidades que o endereço pertence.
	 */
	public Cidade getCidade() {
		return this.cidade;
	}

	/**
	 * Metodo set().
	 * @param cidade - Object - cidades que o endereço pertence.
	 */
	public void setCidade(final Cidade cidade) {
		this.cidade = cidade;
	}

	/**
	 * Metodo get()
	 * @return cliente - Object - cliente que possui este endereço.
	 */
	public Cliente getCliente() {
		return this.cliente;
	}

	/**
	 * Metodo set().
	 * @param cliente - Object - cliente que possui este endereço.
	 */
	public void setCliente(final Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Metodo hashCode (Comparar objetos por valor)
	 * Faz uma comparação entre o id
	 */		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * Metodo equals (Comparar objetos por valor)
	 * Faz uma comparação entre o id
	 */		
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
