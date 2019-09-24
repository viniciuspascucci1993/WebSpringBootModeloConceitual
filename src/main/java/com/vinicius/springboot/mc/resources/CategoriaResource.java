package com.vinicius.springboot.mc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscarCategoria( @PathVariable Integer id ) {
		
		Categoria categoriaObj = categoriaService.buscar(id);
		
		return ResponseEntity.ok().body(categoriaObj);
		
	}
}
