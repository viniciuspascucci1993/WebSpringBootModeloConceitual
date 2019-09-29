package com.vinicius.springboot.mc.model;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.vinicius.springboot.mc.model.enums.SituacaoPagamento;

/**
 * Classe responsável por identificar os atributos de um pagamento com boleto bancário.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Entity
@JsonTypeName("pagamentoComBoleto")
public class PagamentoComBoleto extends Pagamento { 

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Representa a data do vencimento do boleto.
	 */
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataVencimento;
	
	/**
	 * Representa a data do pagamento do boleto.
	 */
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataPagamento;
	
	/**
	 * Representa a data da emissão do boleto.
	 */
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataEmissaoBoleto;
	
	/**
	 * Construtor vazio.
	 */
	public PagamentoComBoleto() { }

	/**
	 * Construtor baseado na superclasse (Pagamento).
	 * @param idPagamento - Integer - idPedido.
	 * @param situacaoPagamento - Enum - Situação do Pagamento.
	 * @param pedido - Object - Pedido
	 * @param dataVencimento - Date - data do vencimento do boleto.
	 * @param dataPagamento - Date - Data do pagamento do boleto.
	 * @param dataEmissaoBoleto - Date - Data da emissão do boleto.
	 */
	public PagamentoComBoleto(Integer idPagamento, SituacaoPagamento situacaoPagamento, Pedido pedido, Date dataVencimento, Date dataPagamento,
			Date dataEmissaoBoleto) {
		super(idPagamento, situacaoPagamento, pedido);
		this.dataEmissaoBoleto = dataEmissaoBoleto;
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
	}

	/**
	 * Metodo().
	 * @return dataVencimento - Date - data do vencimento do boleto.
	 */
	public Date getDataVencimento() {
		return this.dataVencimento;
	}

	/**
	 * Metodo set().
	 * @param dataVencimento - Date - data do vencimento do boleto.
	 */
	public void setDataVencimento(final Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	/**
	 * Metodo get().
	 * @return dataPagamento - Date - data do pagamento do boleto.
	 */
	public Date getDataPagamento() {
		return this.dataPagamento;
	}

	/**
	 * Metodo set().
	 * @param dataPagamento - Date - data do pagamento do boleto.
	 */
	public void setDataPagamento(final Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	/**
	 * Metodo get().
	 * @return dataEmissaoBoleto - Date - data que foi emitido o boleto.
	 */
	public Date getDataEmissaoBoleto() {
		return this.dataEmissaoBoleto;
	}

	/**
	 * Metodo set().
	 * @param dataEmissaoBoleto - Date - data que foi emitido o boleto.
	 */
	public void setDataEmissaoBoleto(final Date dataEmissaoBoleto) {
		this.dataEmissaoBoleto = dataEmissaoBoleto;
	}
	
}
