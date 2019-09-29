package com.vinicius.springboot.mc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinicius.springboot.mc.model.Pedido;
import com.vinicius.springboot.mc.repositories.PedidoRepository;
import com.vinicius.springboot.mc.services.exception.ObjectNotFoundException;

/**
 * Service responsável por nossas operações de CRUD.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	/**
	 * Metodo para buscar pelo id do pedido.
	 * @param id - Integer - id do pedido.
	 * @return Pedido.
	 */		
	public Pedido buscar( Integer id )  {
		
		Optional<Pedido> pedidoObj = pedidoRepository.findById(id);
		return pedidoObj.orElseThrow(() -> new ObjectNotFoundException (
				"Pedido não encontrado! Identificador: " + id + " Tipo do objeto: " + Pedido.class.getName()));
	}
}
