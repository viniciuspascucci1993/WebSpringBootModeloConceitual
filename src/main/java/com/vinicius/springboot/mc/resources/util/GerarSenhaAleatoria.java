package com.vinicius.springboot.mc.resources.util;

import java.util.Random;

/**
 * Classe responsável por gerar uma senha aleatoria para o usuario.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
public class GerarSenhaAleatoria {
	
	private static final Random rand = new Random();
	
	public static String generatePasswordNew() {
		char[] vetor = new char[10];
		
		for (int i = 0; i < 10; i++) {
			
			vetor[i] = generateRandomChar();
		}
		
		return new String(vetor);
	}

	private static char generateRandomChar() {
		
		int opt = rand.nextInt(3);
		
		if (opt == 0) { // gera um digito
			return (char) (rand.nextInt(10) + 48);
			
		} else if (opt ==1 ) { // gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);
			
		} else { // gera letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}
	}

}
