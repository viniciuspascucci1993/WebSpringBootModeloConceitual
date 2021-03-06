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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vinicius.springboot.mc.dto.CategoriaDTO;
import com.vinicius.springboot.mc.model.Categoria;
import com.vinicius.springboot.mc.services.CategoriaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Classe CategoriaResource que representa os nossos serviços REST.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Api(value = "API REST modelo conceitual e estudo de caso - Model Categorias")
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;
	
	/**
	 * Metodo GET para requisições de consulta.
	 * @param id - Integer - id da categoria.
	 * @return ResponseEntity<Categoria>
	 */ 
	@ApiOperation(value = "Metodo GET para requisições de consulta.", code = 200)
	@RequestMapping( produces = "application/json", value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> find( @PathVariable Integer id ) {
		
		Categoria categoriaObj = service.find(id);
		
		return ResponseEntity.ok().body(categoriaObj);
	}
	
	/**
	 * Metodo POST para inserir uma nova categoria.
	 * @param categoriaObj - Object - categoriaObj.
	 * @return ResponseEntity.created.
	 */
	@ApiOperation(value = "Metodo POST para inserir uma nova categoria. Obs: Apenas quem for ADMIN consegue inserir uma nova categoria", code = 201)
	@PreAuthorize("hasAnyRole('ADMIN')") 
	@RequestMapping( produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Void> insert( @Valid @RequestBody CategoriaDTO objDto ) {
		
		Categoria objeto = service.convertFromDTO(objDto);
		
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
	@ApiOperation(value = "Metodo PUT para atualizar uma nova categoria. Obs: Apenas quem for ADMIN consegue atualizar uma categoria", code = 204)
	@PreAuthorize("hasAnyRole('ADMIN')") 	
	@RequestMapping( produces = "application/json", value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update( @Valid @RequestBody CategoriaDTO objDto, @PathVariable Integer id ) {
		
		Categoria objeto = service.convertFromDTO(objDto);
		
		objeto.setId( id ); 
		
		objeto = service.update( objeto );
		
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Metodo DELETE para excluir uma categoria.
	 * @param id - Integer - id da categoria.
	 * @return ResponseEntity<Void>
	 */
	@ApiOperation(value = "Metodo DELETE para excluir uma categoria. Obs: Apenas quem for ADMIN consegue deletar uma categoria", code = 204)
	@PreAuthorize("hasAnyRole('ADMIN')") 
	@RequestMapping( produces = "application/json", value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete( @PathVariable Integer id ) {
		
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	

	/**
	 * Metodo GET para listar todas as categorias.
	 * @return ResponseEntity<Categoria>
	 */
	@ApiOperation(value = "Metodo GET para listar todas as categorias.", code = 200)
	@RequestMapping( produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		
		List<Categoria> lista = service.findAll();
		
		List<CategoriaDTO> listaDto = lista.stream()
					.map(categoriaObj -> new CategoriaDTO(categoriaObj)).collect(Collectors.toList()); // Assim convertemos uma lista para outta lista
		
		return ResponseEntity.ok().body(listaDto);
	}
	
	/**
	 * Metodo GET para paginação das categorias.
	 * @return ResponseEntity.ok().body(listaDto).
	 */
	@ApiOperation(value = "Metodo GET para paginação das categorias.", code = 200)
	@RequestMapping( produces = "application/json", value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nomeCategoria") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		
		Page<Categoria> lista = service.findPage(page, linesPerPage, orderBy, direction);
		
		Page<CategoriaDTO> listaDto = lista.map(categoriaObj -> new CategoriaDTO(categoriaObj)); // Assim convertemos uma lista para outta lista
		
		return ResponseEntity.ok().body(listaDto);
	}

}
