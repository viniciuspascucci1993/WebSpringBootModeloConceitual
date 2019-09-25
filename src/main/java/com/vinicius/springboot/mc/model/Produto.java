package com.vinicius.springboot.mc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vinicius.springboot.mc.model.enums.SituacaoProduto;

/**
 * Classe responsável por identificar os atributos de um produto.
 * @author Vinicius-PC - Viniocius Torres Pascucci.
 */
@Entity
public class Produto implements Serializable {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Representa o identificador do produto.
	 */
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/**
	 * Representa o nome do produto.
	 */
	private String nomeProduto;
	
	/**
	 * Representa o preço do produto.
	 */
	private double preco;
	
	/**
	 * Representa a quantidade que este produto tem ( Quanto tem no estoque ).
	 */
	private Integer quantidade;
	
	/**
	 * Representa o peso do produto.
	 */
	private double peso;
	
	/**
	 * Representa a cor do produto.
	 */
	private String cor;
	
	/**
	 * Representa se este produto está disponível ou não;
	 */
	private Integer disponivel;
	
	/**
	 * Representa uma lista de categorias.
	 */
	@JsonBackReference
	@ManyToMany
	@JoinTable( name = "PRODUTO_CATEGORIA",
	joinColumns = @JoinColumn( name = "produto_id"), 
	inverseJoinColumns = @JoinColumn( name = "categoria_id") 
			)
	private List<Categoria> categorias = new ArrayList<Categoria>();
	
	/**
	 * Construtor vazio.
	 */
	public Produto() { }

	/**
	 * Construtor com argumentos.
	 * @param id - Integer - Identificador.
	 * @param nomeProduto - String - nome do produto.
	 * @param preco - double - preço do produto.
	 * @param quantidade - Integer - quantidade do produto.
	 * @param peso - double - peso do produto.
	 * @param cor - String - cor do produto.
	 * @param disponivel - boolean - disponivel ou não.
	 */
	public Produto(Integer id, String nomeProduto, double preco, Integer quantidade, double peso, String cor, SituacaoProduto disponivel) {
		super();
		this.id = id;
		this.nomeProduto = nomeProduto;
		this.preco = preco;
		this.quantidade = quantidade;
		this.peso = peso;
		this.cor = cor;
		this.disponivel = disponivel.getCodigoSituacao();
	}

	/**
	 * Metodo get().
	 * @return - Integer - identificador do produto.
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * Metodo set().
	 * @param id - Integer - identificador do produto.
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * Metodo get()
	 * @return nomeProduto - String - nome do produto.
	 */
	public String getNomeProduto() {
		return this.nomeProduto;
	}

	/**
	 * Metodo set().
	 * @param nomeProduto - String - nome do produto.
	 */
	public void setNomeProduto(final String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	/**
	 * Metodo get().
	 * @return preco - double - preço do produto.
	 */
	public double getPreco() {
		return this.preco;
	}

	/**
	 * Metodo set().
	 * @param preco - double - preço do produto.
	 */
	public void setPreco(final double preco) {
		this.preco = preco;
	}

	/**
	 * Metodo get().
	 * @return quantidade - double - quantidade do produto.
	 */
	public Integer getQuantidade() {
		return this.quantidade;
	}

	/**
	 * Metodo set().
	 * @param quantidade - double - quantidade do produto
	 */
	public void setQuantidade(final Integer quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * Metodo get().
	 * @return peso - double - peso do produto.
	 */
	public double getPeso() {
		return this.peso;
	}

	/**
	 * Metodo set().
	 * @param peso - double - peso do produto.
	 */
	public void setPeso(final double peso) {
		this.peso = peso;
	}

	/**
	 * Metodo get().
	 * @return cor - String - cor do produto.
	 */
	public String getCor() {
		return this.cor;
	}

	/**
	 * Metodo set().
	 * @param cor - String - cor do produto.
	 */
	public void setCor(final String cor) {
		this.cor = cor;
	}

	/**
	 * Metodo get().
	 * @return disponivel - boolean - verifica se produto esta disponivel ou não.
	 */
	public SituacaoProduto isDisponivel() {
		return SituacaoProduto.toEnum(disponivel);
	}

	/**
	 * Metodo set().
	 * @param disponivel - boolean - verifica se produto esta disponivel ou não.
	 */
	public void setDisponivel(final SituacaoProduto disponivel) {
		this.disponivel = disponivel.getCodigoSituacao();
	}
	
	/**
	 * Metodo get().
	 * @return categorias - List<Categoria> - representa uma lista de categorias.
	 */
	public List<Categoria> getCategorias() {
		return this.categorias;
	}

	/**
	 * Metodo set().
	 * @param categorias - List<Categoria> - representa uma lista de categorias.
	 */
	public void setCategorias(final List<Categoria> categorias) {
		this.categorias = categorias;
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
