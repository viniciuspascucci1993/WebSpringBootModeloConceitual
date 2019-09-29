package com.vinicius.springboot.mc.model;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.vinicius.springboot.mc.model.enums.SituacaoPagamento;

/**
 * Classe responsável por identificar os atributos de pagamento com cartão de crédito.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Representa o numero de parcelas.
	 */
	private Integer numeroParcelas;
	
	/**
	 * Construtor vazio.
	 */
	public PagamentoComCartao() { }

	/**
	 * Construtor baseado na superclasse (Pagamento).
	 * @param idPagamento - Integer - idPagamento.
	 * @param situacaoPagamento - Enum - Situação do pagamento.
	 * @param pedido - Obecjt - pedido. 
	 * @param numeroParcelas - Integer - numeroParcelas
	 */
	public PagamentoComCartao(Integer idPagamento, SituacaoPagamento situacaoPagamento, Pedido pedido, Integer numeroParcelas) {
		super(idPagamento, situacaoPagamento, pedido);
		this.numeroParcelas = numeroParcelas;
	}

	/**
	 * Metodo get().
	 * @return numeroParcelas - Integer - numero de parcelas.
	 */
	public Integer getNumeroParcelas() {
		return this.numeroParcelas;
	}

	/**
	 * Metodo set().
	 * @param numeroParcelas - Integer - numero de parcelas.
	 */
	public void setNumeroParcelas(final Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}
	
	
}
