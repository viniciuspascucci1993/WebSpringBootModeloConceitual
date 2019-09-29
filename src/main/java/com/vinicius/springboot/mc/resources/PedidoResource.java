package com.vinicius.springboot.mc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vinicius.springboot.mc.model.Pedido;
import com.vinicius.springboot.mc.services.PedidoService;

/**
 * Classe PedidoResource que representa os nossos serviços REST.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService pedidoService;
	
	/**
	 * Metodo GET para requisições de consulta
	 * @param id - Integer - id do pedido.
	 * @return ResponseEntity<Pedido>
	 */	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pedido> buscarPedido( @PathVariable Integer id ) {
		
		Pedido categoriaObj = pedidoService.buscar(id);
		
		return ResponseEntity.ok().body(categoriaObj);
		
	}
}
