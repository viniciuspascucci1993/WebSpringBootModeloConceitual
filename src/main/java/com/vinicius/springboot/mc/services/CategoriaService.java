package com.vinicius.springboot.mc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.vinicius.springboot.mc.model.Categoria;
import com.vinicius.springboot.mc.repositories.CategoriaRepository;
import com.vinicius.springboot.mc.services.exception.DataIntegrityException;
import com.vinicius.springboot.mc.services.exception.ObjectNotFoundException;

/**
 * Service responsável por nossas operações de CRUD.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	/**
	 * Metodo para buscar pelo id uma categoria.
	 * @param id - Integer - id da categoria.
	 * @return categoriaObj.
	 */
	public Categoria buscar( Integer id )  {
		
		Optional<Categoria> categoriaObj = categoriaRepository.findById(id);
		return categoriaObj.orElseThrow(() -> new ObjectNotFoundException (
				"Categoria não encontrada! Identificador: " + id + " Tipo do objeto: " + Categoria.class.getName()));
	}
	
	/**
	 * Metodo para inserir uma categoria.
	 * @param id - Integer - id da categoria.
	 * @return categoriaObj.
	 */
	public Categoria inserir( Categoria categoriaObj ) {
		
		categoriaObj.setId( null );
		
		return categoriaRepository.save( categoriaObj );
	}
	
	/**
	 * Metodo para atualizar uma categoria.
	 * @param id - Integer - id da categoria.
	 * @return categoriaObj.
	 */
	public Categoria atualizar( Categoria categoriaObj) {
		
		buscar(categoriaObj.getId());
		
		return categoriaRepository.save( categoriaObj );
	}
	
	/**
	 * Metodo para excluir uma categoria.
	 * @param id - Integer - id da categoria.
	 */
	public void excluir(Integer id) {
		
		buscar(id);
		
		try {
			categoriaRepository.deleteById(id);
			
			
		} catch (DataIntegrityViolationException e) {
			
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos");
		}
	}
	
	/**
	 * Metodo para buscar todas as categorias.
	 */
	public List<Categoria> buscarTodasCategorias() {
		
		return categoriaRepository.findAll();
		
	}
}
