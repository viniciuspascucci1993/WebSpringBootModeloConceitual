package com.vinicius.springboot.mc.resources.util;

import java.util.Random;

/**
 * Classe auxiliar para nos ajduar a gerar um código aleatório para produto.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
public class GeradorCodigoUtil {
	
	public static Integer gerarNumeroAleatorio( Integer num ) {
		
		//instância um objeto da classe Random usando o construtor padrão.
		Random gerador = new Random();
		
		//imprime sequência de 1 numero inteiro aleatório.
		for (int i = 0; i < 10; i++) {
			 gerador.nextInt(26);
		}
		return num;
	}

}
