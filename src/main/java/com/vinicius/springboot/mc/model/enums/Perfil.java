package com.vinicius.springboot.mc.model.enums;

/**
 * Enum responsável por enumerar qual o tipo de pagamento.
 * @author Vinicius-PC - Vinicius Torres Pascucci
 */
public enum Perfil {
	
	ADMIN(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");
	
	/**
	 * Representa o codigo.
	 */
	private int codigo;
	
	/**
	 * Representa a descrição
	 */
	private String descricao;
	
	/**
	 *  Construtor Enumerado com argumentos
	 * @param cod - int - codigo.
	 * @param descricao - String - descrição.
	 */
	private Perfil( int codigo, String descricao ) { 
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
	public static Perfil toEnum( Integer cod ) {
		
		if (cod == null) {
			return null;
		}
		
		/**
		 * percorrer todos os valores possíveis do tipo enumerado.
		 */
		for (Perfil x : Perfil.values()) {
			
			 if (cod.equals(x.getCodigo())) {
				
				 return x;
			}
		}
		
		throw new IllegalArgumentException("Identificador inválido: " + cod);
	}

}
