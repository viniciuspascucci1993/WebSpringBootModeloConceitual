package com.vinicius.springboot.mc.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Utilitária para nos auxiliar na paginação convertendo uma lista de ids para passar como parâmetro na requisição.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
public class UrlAuxiliarUtil {
	
	
	// Metodo para descodificar uma string caso seja passado na nossa requisição um espaço em branco. Ex: TV LED.
	public static String decodeParam( String decodeParam) {
		
		try {
			return URLDecoder.decode(decodeParam, "UTF-8");
		
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	// Metodo que irá pegar na nossa URL da requisição Ex. http://localhost:8080/produtos/nomeProduto=computador&categorias=1,3,4 , esse numero separado por virgula será convertido em uma lista de numeros inteiros.
	public static List<Integer> decodeList( String decode ) {
		
		String[] vetor = decode.split(","); // função spli() pega e recorta em pedaços baseados na virgula.
		
		List<Integer> lista = new ArrayList<>();
		
		for (int i = 0; i < vetor.length; i++) {
			
			lista.add(Integer.parseInt(vetor[i]));
		}
		return lista;
		
		//A instrunção acima pode ser substituida por uma expressão lambda
//		return Arrays.asList(decode.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
	}
}
