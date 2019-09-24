package com.vinicius.springboot.mc.model;

import java.io.Serializable;

/**
 * Classe responsável por identificar uma categoria.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
public class Categoria implements Serializable{

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Representa o Identificador da categoria.
	 */
	private Integer id;
	
	/**
	 * Representa o nome da categoria.
	 */
	private String nomeCategoria;
	
	/**
	 * Construtor vazio.
	 */
	public Categoria() { }

	/**
	 * Contrutor usando argumentos.
	 * @param id - Integer.
	 * @param nomeCategoria - String.
	 */
	public Categoria(Integer id, String nomeCategoria) {
		super();
		this.id = id;
		this.nomeCategoria = nomeCategoria;
	}

	/**
	 * Metodo get().
	 * @return id - Integer - Representa o identificador.
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * Metodo set().
	 * @param id - Integer - Representa o identificador.
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * Metodo get().
	 * @return nomeCategoria - String - Representa o nome da categoria.
	 */
	public String getNomeCategoria() {
		return this.nomeCategoria;
	}

	/**
	 *Metodo set(). 
	 * @param nomeCategoria - String - Representa o nome da categoria.
	 */
	public void setNomeCategoria(final String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
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
		Categoria other = (Categoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
