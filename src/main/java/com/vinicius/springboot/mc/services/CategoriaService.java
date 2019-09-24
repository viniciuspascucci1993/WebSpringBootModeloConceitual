package com.vinicius.springboot.mc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinicius.springboot.mc.model.Categoria;
import com.vinicius.springboot.mc.repositories.CategoriaRepository;

/**
 * Service responsável por nossas operações de CRUD.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria buscar( Integer id ) {
		
		Optional<Categoria> categoriaObj = categoriaRepository.findById(id);
		return categoriaObj.orElse(null);
	}
}
