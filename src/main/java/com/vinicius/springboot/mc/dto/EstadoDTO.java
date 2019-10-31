package com.vinicius.springboot.mc.dto;

import java.io.Serializable;

import com.vinicius.springboot.mc.model.Estado;

/**
 * Classe responsável por transportar apenas os atributos necessarios para nossas operações.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
public class EstadoDTO implements Serializable {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Representa o identificador do estado.
	 */
	private Integer id;
	
	/**
	 * Representa o nome do estado;
	 */
	private String nomeEstado;
	
	/**
	 * Construtor vazio.
	 */
	public EstadoDTO() { }
	
	/**
	 * Construtor com argumentos.
	 * @param obj.
	 */
	public EstadoDTO( Estado obj ) {
		id = obj.getId();
		nomeEstado = obj.getNomeEstado();
	}
	
	/**
	 * Metodo get().
	 * @return id - Integer - identificador estado.
	 */	
	public Integer getId() {
		return this.id;
	}

	/**
	 * Metodo set().
	 * @param id - Integer - identificador estado.
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * Metodo get().
	 * @return nomeEstado - String - nome do estado.
	 */
	public String getNomeEstado() {
		return this.nomeEstado;
	}

	/**
	 * Metodo set().
	 * @param nomeEstado - String - nome do estado.
	 */
	public void setNomeEstado(final String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}
}
