package com.vinicius.springboot.mc.model.enums;

/**
 * Enum responsável por enumerar um tipo de Cliente (Pessoa física ou Pessoa Jurídica).
 * @author Vinicius-PC - Vinicius Torres Pascucci
 */
public enum TipoCliente {

	PESSOA_FISICA(1, "Pessoa Física"),
	PESSOA_JURIDICA(2, "Pessoa Jurídica");
	
	/**
	 * Representa o codigo.
	 */
	private int codigo;
	
	/**
	 * Representa a descrição
	 */
	private String descricao;
	
	/**
	 * Construtor Enumerado com argumentos
	 * @param codigo - int - codigo.
	 * @param descricao - String - descrição.
	 */
	private TipoCliente( int codigo, String descricao ) { 
		this.codigo = codigo;
		this.descricao = descricao;
	}

	/**
	 * Metodo get().
	 * @return codigo - int - codigo da enumeração (TipoCliente).
	 */
	public int getCodigo() {
		return this.codigo;
	}

	/**
	 * Metodo get().
	 * @return descricao - String - descrição da enumeração (TipoCliente).
	 */
	public String getDescricao() {
		return this.descricao;
	} 
	
	/**
	 * Metodo que recebe um codigo do tipo do cliente e me retorna um objeto do tipo TipoCliente.
	 * @param codigo
	 * @return TipoCliente - Enum.
	 */
	public static TipoCliente toEnum( Integer cod ) {
		
		if (cod == null) {
			return null;
		}
		
		/**
		 * percorrer todos os valores possíveis do tipo enumerado.
		 */
		for (TipoCliente x : TipoCliente.values()) {
			
			 if (cod.equals(x.getCodigo())) {
				
				 return x;
			}
		}
		
		throw new IllegalArgumentException("Identificador inválido: " + cod);
	}
	
}
