package com.vinicius.springboot.mc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * Classe responsável por identificar os atributos de uma categoria.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Entity
public class Categoria implements Serializable {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Representa o identificador da categoria.
	 */
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/**
	 * Representa o nome da categoria.
	 */
	private String nomeCategoria;
	
	/**
	 * Representa uma lista de produtos;
	 */
	@ManyToMany( mappedBy = "categorias")
	private List<Produto> produtos = new ArrayList<Produto>();
	
	/**
	 * Construtor vazio.
	 */
	public Categoria() { }

	/**
	 * Contrutor usando argumentos.
	 * @param id - Integer - identificador da categoria.
	 * @param nomeCategoria - String.
	 */
	public Categoria(Integer id, String nomeCategoria) {
		super();
		this.id = id;
		this.nomeCategoria = nomeCategoria;
	}

	/**
	 * Metodo get().
	 * @return id - Integer - identificador da categoria.
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * Metodo set().
	 * @param id - Integer - identificador da categoria.
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * Metodo get().
	 * @return nomeCategoria - String - nome da categoria.
	 */
	public String getNomeCategoria() {
		return this.nomeCategoria;
	}

	/**
	 *Metodo set(). 
	 * @param nomeCategoria - String - nome da categoria..
	 */
	public void setNomeCategoria(final String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	
	
	/**
	 * Metodo get().
	 * @return produtos - List<Produto> - representa uma lista de produtos
	 */
	public List<Produto> getProdutos() {
		return this.produtos;
	}

	/**
	 * Metodo set().
	 * @param produtos - List<Produto> - representa uma lista de produtos
	 */
	public void setProdutos(final List<Produto> produtos) {
		this.produtos = produtos;
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
