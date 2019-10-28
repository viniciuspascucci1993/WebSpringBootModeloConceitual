package com.vinicius.springboot.mc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vinicius.springboot.mc.dto.ClienteDTO;
import com.vinicius.springboot.mc.dto.ClienteNewDTO;
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
	private ClienteService service;
	
	/**
	 * Metodo GET para requisições de consulta
	 * @param id - Integer - id do cliente.
	 * @return ResponseEntity<Cliente>
	 */	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> find( @PathVariable Integer id ) {
		
		Cliente clienteObj = service.find(id);
		
		return ResponseEntity.ok().body(clienteObj);
		
	}
	
	/**
	 * Metodo POST para inserir um cliente.
	 * @param clienteObj - Object - clienteaObj.
	 * @return ResponseEntity.created.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert( @Valid @RequestBody ClienteNewDTO objDto ) {
		
		Cliente objeto = service.convertFromDTO(objDto);
		
		objeto = service.insert( objeto );
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(objeto.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	/**
	 * Metodo PUT para atualizar uma nova categoria.
	 * @param categoriaObj - Object - categoriaObj.
	 * @return ResponseEntity.created.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update( @Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id ) {
		
		Cliente objeto = service.convertFromDTO(objDto);
		
		objeto.setId( id ); 
		
		objeto = service.update( objeto );
		
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Metodo DELETE para excluir uma categoria.
	 * @param id - Integer - id da categoria.
	 * @return ResponseEntity<Void>
	 */
	@PreAuthorize("hasAnyRole('ADMIN')") 
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete( @PathVariable Integer id ) {
		
		service.excluir(id);
		
		return ResponseEntity.noContent().build();
	}
	

	/**
	 * Metodo GET para listar todas as categorias.
	 * @return ResponseEntity<Cliente>
	 */
	@PreAuthorize("hasAnyRole('ADMIN')") 
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll() {
		
		List<Cliente> lista = service.findAll();
		
		List<ClienteDTO> listaDto = lista.stream()
					.map(categoriaObj -> new ClienteDTO(categoriaObj)).collect(Collectors.toList()); // Assim convertemos uma lista para outta lista
		
		return ResponseEntity.ok().body(listaDto);
	}
	
	/**
	 * Metodo GET para paginação das categorias.
	 * @return Page
	 */
	@PreAuthorize("hasAnyRole('ADMIN')") 
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> encontrarClientesPorPaginacao(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nomeCliente") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		
		Page<Cliente> lista = service.findPage(page, linesPerPage, orderBy, direction);
		
		Page<ClienteDTO> listaDto = lista.map(categoriaObj -> new ClienteDTO(categoriaObj)); // Assim convertemos uma lista para outta lista
		
		return ResponseEntity.ok().body(listaDto);
	}
	
	/**
	 * Metodo para enviar imagem de profile para o S3.
	 * @param file - MultipartFile.
	 * @return ResponseEntity.created(uri).build();
	 */
	@RequestMapping( value = "/picture", method = RequestMethod.POST)
	public ResponseEntity<Void> uploadPicture( @RequestParam(name = "file") MultipartFile file ) {
		
		URI uri = service.uploadProfilePicture(file);
		
		return ResponseEntity.created(uri).build();
	}
}
