package com.vinicius.springboot.mc.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vinicius.springboot.mc.model.Pedido;
import com.vinicius.springboot.mc.services.PedidoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Classe PedidoResource que representa os nossos serviços REST.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Api(value = "API REST modelo conceitual e estudo de caso - Model Pedido")
@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService service;
	
	/**
	 * Metodo GET para requisições de consulta.
	 * @param id - Integer - id do pedido.
	 * @return ResponseEntity<Pedido>
	 */	
	@ApiOperation(value = "Metodo GET para requisições de consulta.", code = 200)
	@RequestMapping( produces = "application/json", value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pedido> find( @PathVariable Integer id ) {
		
		Pedido pedidoObj = service.buscar(id);
		
		return ResponseEntity.ok().body(pedidoObj);
		
	}
	
	/**
	 * Metodo POST para inserir um novo pedido.
	 * @param obj - Object - obj.
	 * @return ResponseEntity.created.
	 */
	@ApiOperation(value = "Metodo POST para inserir um novo pedido.", code = 201)
	@RequestMapping( produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Void> insert( @Valid @RequestBody Pedido obj ) {
			
		obj = service.insert( obj );
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(obj.getIdPedido()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	/**
	 * Metodo GET para paginação das categorias.
	 * @return ResponseEntity.ok().body(listaDto).
	 */
	@ApiOperation(value = "Metodo GET para paginação das categorias.", code = 200)
	@RequestMapping( produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<Page<Pedido>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "horarioPedido") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "DESC") String direction) {
		
		Page<Pedido> lista = service.findPage(page, linesPerPage, orderBy, direction);
		
		return ResponseEntity.ok().body(lista);
	}
}
