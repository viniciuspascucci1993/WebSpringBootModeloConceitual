package com.vinicius.springboot.mc.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vinicius.springboot.mc.model.Categoria;
import com.vinicius.springboot.mc.services.CategoriaService;

/**
 * Classe CategoriaResource que representa os nossos serviços REST.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;
	
	/**
	 * Metodo GET para requisições de consulta
	 * @param id - Integer - id da categoria.
	 * @return ResponseEntity.ok
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscarCategoria( @PathVariable Integer id ) {
		
		Categoria categoriaObj = categoriaService.buscar(id);
		
		return ResponseEntity.ok().body(categoriaObj);
	}
	
	/**
	 * Metodo POST para inserir uma nova categoria.
	 * @param categoriaObj - Object - categoriaObj.
	 * @return ResponseEntity.created.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserirCategoria( @RequestBody Categoria categoriaObj ) {
		
		categoriaObj = categoriaService.inserir( categoriaObj );
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(categoriaObj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
}
