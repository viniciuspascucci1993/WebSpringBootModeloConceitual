package com.vinicius.springboot.mc.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	private PedidoService service;
	
	/**
	 * Metodo GET para requisições de consulta
	 * @param id - Integer - id do pedido.
	 * @return ResponseEntity<Pedido>
	 */	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pedido> find( @PathVariable Integer id ) {
		
		Pedido pedidoObj = service.buscar(id);
		
		return ResponseEntity.ok().body(pedidoObj);
		
	}
	
	/**
	 * Metodo POST para inserir uma nova categoria.
	 * @param obj - Object - obj.
	 * @return ResponseEntity.created.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert( @Valid @RequestBody Pedido obj ) {
			
		obj = service.insert( obj );
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(obj.getIdPedido()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
}
