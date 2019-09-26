package com.vinicius.springboot.mc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Classe responsável por identificar os atributos de um pedido.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Entity
public class Pedido implements Serializable{

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Representa o identificador do pedido.
	 */
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Integer idPedido;
	
	/**
	 * Representa o horário do pedido realizado.
	 */
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date horarioPedido;
	
	/**
	 * Representa o pagamento.
	 */
	@OneToOne( cascade = CascadeType.ALL, mappedBy = "pedido")
	private Pagamento pagamento;
	
	/**
	 * Representa o endereço.
	 */
	@ManyToOne
	@JoinColumn( name = "endereco_de_entrega_id")
	private Endereco enderecoDeEntrega;
	
	/**
	 * Representa o cliente.
	 */
	@ManyToOne
	@JoinColumn( name = "cliente_id")
	private Cliente cliente;
	
	/**
	 * Representa uma coleção de item de pedido.
	 */
	@OneToMany(mappedBy = "id.pedido")
	private Set<ItemPedido> items = new HashSet<ItemPedido>();
	
	/**
	 * Construtor vazio.
	 */
	public Pedido() { }

	/**
	 * Construtor com argumentos.
	 * @param idPedido - Integer - id.
	 * @param horarioPedido - Date - hora que foi realizado o pedido.
	 * @param pagamento - String - pagamento.
	 * @param enderecoDeEntrega - Object - endereço de entrega
	 * @param cliente - Object - cliente.
	 */
	public Pedido(Integer idPedido, Date horarioPedido, Endereco enderecoDeEntrega,
			Cliente cliente) {
		super();
		this.idPedido = idPedido;
		this.horarioPedido = horarioPedido;
		this.enderecoDeEntrega = enderecoDeEntrega;
		this.cliente = cliente;
	}

	/**
	 * Metodo get()
	 * @return idPedido - Integer - id do pedido.
	 */
	public Integer getIdPedido() {
		return this.idPedido;
	}

	/**
	 * Metodo set().
	 * @param idPedido - Integer - id do pedido.
	 */
	public void setIdPedido(final Integer idPedido) {
		this.idPedido = idPedido;
	}

	/**
	 * Metodo get().
	 * @return horarioPedido - Date - hota em que foi realizado o pedido.
	 */
	public Date getHorarioPedido() {
		return this.horarioPedido;
	}

	/**
	 * Metodo set().
	 * @param horarioPedido - Date - hota em que foi realizado o pedido.
	 */
	public void setHorarioPedido(final Date horarioPedido) {
		this.horarioPedido = horarioPedido;
	}

	/**
	 * Metodo get().
	 * @return pagamento - Object - pagamento.
	 */
	public Pagamento getPagamento() {
		return this.pagamento;
	}

	/**
	 * Metodo set().
	 * @param pagamento - Object - pagamento.
	 */
	public void setPagamento(final Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	/**
	 * Metodo get().
	 * @return enderecoDeEntrega - Object - endereço de entrega.
	 */
	public Endereco getEnderecoDeEntrega() {
		return this.enderecoDeEntrega;
	}

	/**
	 * Metodo set().
	 * @param enderecoDeEntrega - Object - endereço de entrega.
	 */
	public void setEnderecoDeEntrega(final Endereco enderecoDeEntrega) {
		this.enderecoDeEntrega = enderecoDeEntrega;
	}

	/**
	 * Metodo get().
	 * @return cliente - Object - cliente.
	 */
	public Cliente getCliente() {
		return this.cliente;
	}

	/**
	 * Metodo set().
	 * @param cliente - Object - cliente.
	 */
	public void setCliente(final Cliente cliente) {
		this.cliente = cliente;
	}
	

	/**
	 * Metodo get().
	 * @return items - Set<ItemPedido> - items.
	 */
	public Set<ItemPedido> getItems() {
		return this.items;
	}

	/**
	 * Metodo set().
	 * @param items - Set<ItemPedido> - items.
	 */
	public void setItems(final Set<ItemPedido> items) {
		this.items = items;
	}

	/**
	 * Metodo hashCode (Comparar objetos por valor)
	 * Faz uma comparação entre o id
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPedido == null) ? 0 : idPedido.hashCode());
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
		Pedido other = (Pedido) obj;
		if (idPedido == null) {
			if (other.idPedido != null)
				return false;
		} else if (!idPedido.equals(other.idPedido))
			return false;
		return true;
	}

	
	
	
	

}
