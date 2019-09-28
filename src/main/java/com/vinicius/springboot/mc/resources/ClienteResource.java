package com.vinicius.springboot.mc.resources;

import java.util.List;
import java.util.stream.Collectors;

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

import com.vinicius.springboot.mc.dto.ClienteDTO;
import com.vinicius.springboot.mc.model.Cliente;
import com.vinicius.springboot.mc.services.ClienteService;

/**
 * Classe ClienteResource que representa os nossos serviços REST.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> buscarCliente( @PathVariable Integer id ) {
		
		Cliente clienteObj = clienteService.find(id);
		
		return ResponseEntity.ok().body(clienteObj);
		
	}
	
	/**
	 * Metodo PUT para atualizar uma nova categoria.
	 * @param categoriaObj - Object - categoriaObj.
	 * @return ResponseEntity.created.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update( @Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id ) {
		
		Cliente objeto = clienteService.convertFromDTO(objDto);
		
		objeto.setId( id ); 
		
		objeto = clienteService.update( objeto );
		
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Metodo DELETE para excluir uma categoria.
	 * @param id - Integer - id da categoria.
	 * @return ResponseEntity<Void>
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete( @PathVariable Integer id ) {
		
		clienteService.excluir(id);
		
		return ResponseEntity.noContent().build();
	}
	

	/**
	 * Metodo GET para listar todas as categorias.
	 * @return ResponseEntity<Cliente>
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll() {
		
		List<Cliente> lista = clienteService.findAll();
		
		List<ClienteDTO> listaDto = lista.stream()
					.map(categoriaObj -> new ClienteDTO(categoriaObj)).collect(Collectors.toList()); // Assim convertemos uma lista para outta lista
		
		return ResponseEntity.ok().body(listaDto);
	}
	
	/**
	 * Metodo GET para paginação das categorias.
	 * @return Page
	 */
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> encontrarClientesPorPaginacao(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nomeCliente") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		
		Page<Cliente> lista = clienteService.findPage(page, linesPerPage, orderBy, direction);
		
		Page<ClienteDTO> listaDto = lista.map(categoriaObj -> new ClienteDTO(categoriaObj)); // Assim convertemos uma lista para outta lista
		
		return ResponseEntity.ok().body(listaDto);
	}
}
