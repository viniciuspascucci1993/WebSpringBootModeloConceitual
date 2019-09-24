package com.vinicius.springboot.mc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vinicius.springboot.mc.model.Categoria;

/**
 * Classe CategoriaResource que representa os nossos serviços REST.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 *
 */
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> listar() {
		
		Categoria categoria1 = new Categoria(1, "Informática");
		
		Categoria categoria2 = new Categoria(2, "Escritório");
		
		List<Categoria> lista = new ArrayList<Categoria>();
		
		lista.add(categoria1);
		
		lista.add(categoria2);
		
		return lista;
		
	}
}
