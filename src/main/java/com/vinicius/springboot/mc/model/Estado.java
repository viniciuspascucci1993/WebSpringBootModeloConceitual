package com.vinicius.springboot.mc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe responsável por identificar os atributos de um estado.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Entity
public class Estado implements Serializable{

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Representa o identificador do estado.
	 */
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/**
	 * Representa o nome do estado;
	 */
	private String nomeEstado;
	
	/**
	 * Representa uma lista de cidades.
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "estado")
	private List<Cidade> cidades = new ArrayList<Cidade>();
	
	public Estado() { }

	/**
	 * Constutor com argumentos.
	 * @param id - Integer - id.
	 * @param nomeEstado - String - nome estado.
	 */
	public Estado(Integer id, String nomeEstado) {
		super();
		this.id = id;
		this.nomeEstado = nomeEstado;
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

	/**
	 * Metodo get().
	 * @return cidades - List<Cidade> - lista de cidades.
	 */
	public List<Cidade> getCidades() {
		return this.cidades;
	}

	/**
	 * Metodo set().
	 * @param cidades - List<Cidade> - lista de cidades.
	 */
	public void setCidades(final List<Cidade> cidades) {
		this.cidades = cidades;
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
		Estado other = (Estado) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
