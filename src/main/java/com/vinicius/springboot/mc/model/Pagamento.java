package com.vinicius.springboot.mc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vinicius.springboot.mc.model.enums.SituacaoPagamento;

/**
 * Classe responsável por identificar os atributos de um pagamento.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Entity
@Inheritance( strategy = InheritanceType.JOINED)
public abstract class Pagamento implements Serializable{

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Representa o identificador do pagamento.
	 */
	@Id
	private Integer idPagamento;
	
	/**
	 * Representa o tipo enumerado da situação do pagamento.
	 */
	private Integer situacaoPagamento;
	
	/**
	 * Representa o pedido;
	 */
	@JsonBackReference
	@OneToOne
	@JoinColumn( name = "pedido_id")
	@MapsId
	private Pedido pedido;
	
	/**
	 * Construtor vazio.
	 */
	public Pagamento() { }

	/**
	 * Construtor com argumentos.
	 * @param idPagamento - Integer - id.
	 * @param situacaoPagamento - Enum - Situação do pagamento.
	 * @param pedido - Object - Pedido
	 */
	public Pagamento(Integer idPagamento, SituacaoPagamento situacaoPagamento, Pedido pedido) {
		super();
		this.idPagamento = idPagamento;
		this.situacaoPagamento = situacaoPagamento.getCodigo();
		this.pedido = pedido;
	}

	/**
	 * Metodo get().
	 * @return id - Integer - identificador pagamento.
	 */
	public Integer getIdPagamento() {
		return this.idPagamento;
	}

	/**
	 * Metodo set().
	 * @param idPagamento - Integer - identificador pagamento.
	 */
	public void setIdPagamento(final Integer idPagamento) {
		this.idPagamento = idPagamento;
	}

	/**
	 * Metodo get().
	 * @return situacaoPagamento - Enum - situação do pagamento.
	 */
	public SituacaoPagamento getSituacaoPagamento() {
		return SituacaoPagamento.toEnum(situacaoPagamento);
	}

	/**
	 * Metodo set().
	 * @param situacaoPagamento - Enum - situação do pagamento.
	 */
	public void setSituacaoPagamento(final SituacaoPagamento situacaoPagamento) {
		this.situacaoPagamento = situacaoPagamento.getCodigo();
	}

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
	 * Metodo hashCode (Comparar objetos por valor)
	 * Faz uma comparação entre o id
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPagamento == null) ? 0 : idPagamento.hashCode());
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
		Pagamento other = (Pagamento) obj;
		if (idPagamento == null) {
			if (other.idPagamento != null)
				return false;
		} else if (!idPagamento.equals(other.idPagamento))
			return false;
		return true;
	}
	
	
	
	
}
