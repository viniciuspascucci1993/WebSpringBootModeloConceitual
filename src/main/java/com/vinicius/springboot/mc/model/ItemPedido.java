package com.vinicius.springboot.mc.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe responsável por identificar os atributos de um item de pedido.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Entity
public class ItemPedido implements Serializable{

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Representa uma chave composta de OtemPedidoPK
	 */
	@JsonIgnore
	@EmbeddedId
	private ItemPedidoPK id = new ItemPedidoPK();
	
	/**
	 * Representa o desconto do itme de pedido.
	 */
	private Double desconto;
	
	/**
	 * Representa a quantidade do item de pedido.
	 */
	private Integer quantidade;
	
	/**
	 * Representa o preço do item de pedido.
	 */
	private Double preco;
	
	/**
	 * Representa a descriçao do item de pedido.
	 */
	private String descricaoItemPedido;
	
	/**
	 * Construtor vazio.
	 */
	public ItemPedido() { }

	/**
	 * Construtor com argumentos.
	 * @param id - Integer - id.
	 * @param desconto - Double - desconto. 
	 * @param quantidade - Integer - quantidade.
	 * @param descricaoItemPedido - String - descrição item.
	 */
	public ItemPedido(Pedido pedido, Produto produto, Double descotno, Double preco, Integer quantidade, String descricaoItemPedido) {
		super();
		id.setPedido(pedido);
		id.setProduto(produto); 
		this.desconto = descotno;
		this.preco = preco;
		this.quantidade = quantidade;
		this.descricaoItemPedido = descricaoItemPedido;
	}
	
	/**
	 * Metodo get()
	 * @return subTotal - diuble - retorna o subTotal dos items.
	 */
	public double getSubTotal() {
		return ( preco - desconto ) * quantidade;
	}
	
	/**
	 * Metodo get().
	 * @return pedido - Object - pedido.
	 */
	@JsonIgnore
	public Pedido getPedido() {
		return this.id.getPedido();
	}
	
	/**
	 * Metodo set().
	 * @param pedido - void - pedido.
	 */
	public void setPedido(final Pedido pedido) {
		id.setPedido(pedido);
	}
	
	/**
	 * Metodo get().
	 * @return produto - Object - produto.
	 */	
	public Produto getProduto() {
		return this.id.getProduto();
	}
	
	/**
	 * Metodo set().
	 * @param produto - void - produto.
	 */
	public void setProduto(final Produto produto) {
		id.setProduto(produto);
	}	

	/**
	 * Metodo get().
	 * @return id - ItemPedidoPK - Identificador itemPedido.
	 */
	public ItemPedidoPK getId() {
		return this.id;
	}

	/**
	 * Metodo set().
	 * @param id - Integer - Identificador itemPedido.
	 */
	public void setId(final ItemPedidoPK id) {
		this.id = id;
	}

	/**
	 * Metodo get().
	 * @return desconto - Double - desconto do item.
	 */
	public Double getDesconto() {
		return this.desconto;
	}

	/**
	 * Metodo set().
	 * @param desconto - Double - desconto do item.
	 */
	public void setDesconto(final Double desconto) {
		this.desconto = desconto;
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
	 * @return quantidade - Integer - quantidade do item.
	 */
	public Integer getQuantidade() {
		return this.quantidade;
	}

	/**
	 * Metodo set().
	 * @param quantidade - Integer - quantidade do item.
	 */
	public void setQuantidade(final Integer quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * Metodo get().
	 * @return descricaoItemPedido - String - descrição do item.
	 */
	public String getDescricaoItemPedido() {
		return this.descricaoItemPedido;
	}

	/**
	 * Metodo set().
	 * @param descricaoItemPedido - String - descrição do item.
	 */
	public void setDescricaoItemPedido(final String descricaoItemPedido) {
		this.descricaoItemPedido = descricaoItemPedido;
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
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/**
	 * Metodo toString para itemPedido para transformar o item em uma String.
	 */
	@Override
	public String toString() {
		
		NumberFormat numeroFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		
		StringBuilder builder = new StringBuilder();
		builder.append("Nome Produto: ");
		builder.append(getProduto().getNomeProduto());
		builder.append(", Quantidade: ");
		builder.append(getQuantidade());
		builder.append(", Descrição: ");
		builder.append(getDescricaoItemPedido());
		builder.append(" ");
		builder.append(", Preço unitário: ");
		builder.append(numeroFormat.format(getPreco()));
		builder.append(" ");
		builder.append(", Sub Total: ");
		builder.append(numeroFormat.format(getSubTotal()));
		builder.append("\n");
		return builder.toString();
	}
}
