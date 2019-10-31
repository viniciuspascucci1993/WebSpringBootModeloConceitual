package com.vinicius.springboot.mc.dto;

import java.io.Serializable;

import com.vinicius.springboot.mc.model.Cidade;

/**
 * Classe responsável por transportar apenas os atributos necessarios para nossas operações.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
public class CidadeDTO implements Serializable {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Representa o identificador da cidade.
	 */
	private Integer id;
	
	/**
	 * Representa o nome da cidade.
	 */
	private String nomeCidade;
	
	/**
	 * Construtor vazio
	 */
	public CidadeDTO() { }
	
	public CidadeDTO( Cidade obj) {
		id = obj.getId();
		nomeCidade = obj.getNomeCidade();
	}
	
	/**
	 * Metodo get().
	 * @return id - Integer - identificador cidade.
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * Metodo set().
	 * @param id - Integer - identificador cidade.
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * Metodo get().
	 * @return nomeCidade - String - nome da cidade.
	 */	
	public String getNomeCidade() {
		return this.nomeCidade;
	}

	/**
	 * Metodo set().
	 * @param nomeCidade - String - nome da cidade.
	 */
	public void setNomeCidade(final String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}
}
