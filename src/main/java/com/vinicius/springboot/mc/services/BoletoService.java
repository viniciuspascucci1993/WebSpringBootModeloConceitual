package com.vinicius.springboot.mc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.vinicius.springboot.mc.model.PagamentoComBoleto;

/**
 * Service responsável por consumir os dados de um webService de boleto.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Service
public class BoletoService {

	public void preencherPagamentoComBoleto( PagamentoComBoleto pagamento, Date momentoPedsido) {
		
		Calendar c = Calendar.getInstance();
		
		c.setTime(momentoPedsido);
		
		c.add(Calendar.DAY_OF_MONTH, 7);
		
		pagamento.setDataVencimento(c.getTime());
	}
}
