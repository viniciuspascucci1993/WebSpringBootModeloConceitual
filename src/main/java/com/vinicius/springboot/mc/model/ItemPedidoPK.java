package com.vinicius.springboot.mc.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Classe respónsável composta por representar o Primary Key de um item de pedido
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Embeddable
public class ItemPedidoPK implements Serializable {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Representa o pedido.
	 */
	@ManyToOne
	@JoinColumn( name = "pedido_id")
	private Pedido pedido;
	
	/**
	 * Representa o produto.
	 */
	@ManyToOne
	@JoinColumn( name = "produto_id")	
	private Produto produto;

	/**
	 * Metodo get().
	 * @return pedido - Object - pedido.
	 */
	public Pedido getPedido() {
		return this.pedido;
	}

	/**
	 * Metodo set().
	 * @param pedido - Object - pedido.
	 */
	public void setPedido(final Pedido pedido) {
		this.pedido = pedido;
	}

	/**
	 * Metodo get().
	 * @return produto - Object - produto.
	 */
	public Produto getProduto() {
		return this.produto;
	}

	/**
	 * Metodo set().
	 * @param produto - Object - produto.
	 */
	public void setProduto(final Produto produto) {
		this.produto = produto;
	}

	/**
	 * Metodo hashCode (Comparar objetos por valor)
	 * Faz uma comparação entre produto e pedido.
	 */		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pedido == null) ? 0 : pedido.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		return result;
	}
	
	/**
	 * Metodo equals (Comparar objetos por valor)
	 * Faz uma comparação entre produto e pedido.
	 */	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedidoPK other = (ItemPedidoPK) obj;
		if (pedido == null) {
			if (other.pedido != null)
				return false;
		} else if (!pedido.equals(other.pedido))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		return true;
	}
	
	
}
