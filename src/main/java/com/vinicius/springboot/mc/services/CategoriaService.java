package com.vinicius.springboot.mc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinicius.springboot.mc.model.Categoria;
import com.vinicius.springboot.mc.repositories.CategoriaRepository;
import com.vinicius.springboot.mc.services.exception.ObjectNotFoundException;

/**
 * Service responsável por nossas operações de CRUD.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria buscar( Integer id )  {
		
		Optional<Categoria> categoriaObj = categoriaRepository.findById(id);
		return categoriaObj.orElseThrow(() -> new ObjectNotFoundException (
				"Categoria não encontrada! Identificador: " + id + " Tipo do objeto: " + Categoria.class.getName()));
	}
	
	public Categoria inserir( Categoria categoriaObj ) {
		
		categoriaObj.setId( null );
		
		return categoriaRepository.save( categoriaObj );
	}
}
