package com.vinicius.springboot.mc.dto;

import java.io.Serializable;

import com.vinicius.springboot.mc.model.Produto;

/**
 * Classe responsável por transportar apenas os atributos necessarios para nossas operações.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
public class ProdutoDTO implements Serializable {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
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
	 * Construtor vazio.
	 */
	public ProdutoDTO() { }
	
	/**
	 * Construtor passando um Object Produto.
	 * @param produtoObj
	 */
	public ProdutoDTO( Produto produtoObj) { 
		id = produtoObj.getId();
		nomeProduto = produtoObj.getNomeProduto();
		preco = produtoObj.getPreco();
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
}
