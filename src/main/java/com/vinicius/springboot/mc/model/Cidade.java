package com.vinicius.springboot.mc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Classe responsável por identificar os atributos de uma cidade..
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Entity
public class Cidade implements Serializable {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Representa o id.
	 */
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/**
	 * Representa o nome da cidade.
	 */
	private String nomeCidade;
	
	/**
	 * Representa UM estado.
	 */
	@ManyToOne
	@JoinColumn( name = "estado_id")
	private Estado estado;
	
	public Cidade () { }

	/**
	 * Consturtor com argumentos.
	 * @param id - Integer - id.
	 * @param nomeCidade - String  - nome da cidade.
	 * @param estado - Object - cidade tem um estado.
	 */
	public Cidade(Integer id, String nomeCidade, Estado estado) {
		super();
		this.id = id;
		this.nomeCidade = nomeCidade;
		this.estado = estado;
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

	/**
	 * Metodo get().
	 * @return estado - Object - representa um estado.
	 */
	public Estado getEstado() {
		return this.estado;
	}

	/**
	 * Metodo set().
	 * @param estado - Object - representa um estado.
	 */
	public void setEstado(final Estado estado) {
		this.estado = estado;
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
		Cidade other = (Cidade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
