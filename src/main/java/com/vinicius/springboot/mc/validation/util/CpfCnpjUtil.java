package com.vinicius.springboot.mc.validation.util;

/**
 * Classe Utilitária que nos auxiliar no desenvolvimento do projeto.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
public class CpfCnpjUtil {
    // CPF
    private static final int[] WEIGHT_SSN = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

    // CNPJ
    private static final int[] WEIGHT_TFN = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    
    
    private static int recursiveSum(int[] weight, char[] chr, int number) {
        
    	if (number <= 0) return 0;
        
        final int chrIndex = number - 1;
        
        final int weightIndex = weight.length > chr.length ? number : chrIndex;
        
        return (recursiveSum(weight, chr, chrIndex) +
                Character.getNumericValue(chr[chrIndex]) * weight[weightIndex]);
    }
    
    private static int calculate(final String str, final int[] weight) {
        
    	final char[] chr = str.toCharArray();
        
    	int sum = recursiveSum(weight, chr, chr.length);
        
    	sum = 11 - (sum % 11);
        
    	return sum > 9 ? 0 : sum;
    }
	
    private static boolean checkEquals(String tfn, int length, int[] weight) {
        
    	final String number = tfn.substring(0, length);
        
    	final int digit1 = calculate(number, weight);
        
    	final int digit2 = calculate(number + digit1, weight);
        
    	return tfn.equals(number + digit1 + digit2);
    }

    /**
     * Valida CPF.
     * @param strCpf.
     * @return strCpf.
     */
    public static boolean isValidCpf(String strCpf) {
        if (strCpf == null || !strCpf.matches("\\d{11}") || strCpf.matches(strCpf.charAt(0) + "{11}")) return false;
        return checkEquals(strCpf, 9, WEIGHT_SSN);
    }

    /**
     * Valida CNPJ.
     * @param strCnpj.
     * @return strCnpj.
     */
    public static boolean isValidCnpj(String strCnpj) {
        if (strCnpj == null || !strCnpj.matches("\\d{14}") || strCnpj.matches(strCnpj.charAt(0) + "{14}")) return false;
        return checkEquals(strCnpj, 12, WEIGHT_TFN);
    }
}