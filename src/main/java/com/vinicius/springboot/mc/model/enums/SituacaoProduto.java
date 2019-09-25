package com.vinicius.springboot.mc.model.enums;

/**
 * Enum responsável por enumerar a situação do produto em estoque.
 * @author Vinicius-PC - Vinicius Torres Pascucci
 */
public enum SituacaoProduto {

	DISPONIVEL(1, "Disponível"),
	NAO_DISPONIVEL(2, "Não-disponível");
	
	/**
	 * Representa o codigo da situação do produto.
	 */
	private int codigoSituacao;
	
	/**
	 * Representa a descrição
	 */
	private String descricao;
	
	/**
	 * Construtor Enumerado com argumentos
	 * @param codigoSituacao - int - codigo da situação. 
	 * @param descricao - String - descrição da situação.
	 */
	private SituacaoProduto( int codigoSituacao, String descricao ) { 
		this.codigoSituacao = codigoSituacao;
		this.descricao = descricao;
	}

	/**
	 * Metodo get().
	 * @return codigoSituacao - int - codigo da situação.
	 */
	public int getCodigoSituacao() {
		return this.codigoSituacao;
	}

	/**
	 * Metodo set().
	 * @return descricao - String - descrição.
	 */
	public String getDescricao() {
		return this.descricao;
	}
	
	/**
	 * Metodo que recebe um codigo da situação do produto e me retorna um objeto do tipo SituaçãoProduto.
	 * @param codigo
	 * @return TipoCliente - Enum.
	 */
	public static SituacaoProduto toEnum( Integer cod ) {
		
		if (cod == null) {
			return null;
		}
		
		/**
		 * percorrer todos os valores possíveis do tipo enumerado.
		 */
		for (SituacaoProduto produto : SituacaoProduto.values()) {
			
			if (cod.equals(produto.getCodigoSituacao())) {
				return produto;
			}
		}
		
		throw new IllegalArgumentException("Identificador inválido: " + cod);
	}
	
}
