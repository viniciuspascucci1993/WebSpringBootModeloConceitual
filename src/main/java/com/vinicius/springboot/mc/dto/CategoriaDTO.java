package com.vinicius.springboot.mc.dto;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.vinicius.springboot.mc.model.Categoria;

public class CategoriaDTO implements Serializable {

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
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 80, message = "O Tamanho deve ser entre 5 e 80 caracteres")
	private String nomeCategoria; 
	
	public CategoriaDTO() { }
	
	/**
	 * Construtor passando um Object Categoria.
	 * @param categoriaoBK
	 */
	public CategoriaDTO(Categoria categoriaObj) {
		id = categoriaObj.getId();
		nomeCategoria = categoriaObj.getNomeCategoria();
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
	
}
