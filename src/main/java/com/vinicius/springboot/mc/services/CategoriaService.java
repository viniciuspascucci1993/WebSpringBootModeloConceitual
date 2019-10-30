package com.vinicius.springboot.mc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.vinicius.springboot.mc.dto.CategoriaDTO;
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
	public Categoria find( Integer id )  {
		
		Optional<Categoria> categoriaObj = categoriaRepository.findById(id);
		return categoriaObj.orElseThrow(() -> new ObjectNotFoundException (
				"Categoria não encontrada! Identificador: " + id + " Tipo do objeto: " + Categoria.class.getName()));
	}
	
	/**
	 * Metodo para inserir uma categoria.
	 * @param id - Integer - id da categoria.
	 * @return categoriaObj.
	 */
	public Categoria insert( Categoria categoriaObj ) {
		
		categoriaObj.setId( null );
		
		return categoriaRepository.save( categoriaObj );
	}
	
	/**
	 * Metodo para atualizar uma categoria.
	 * @param id - Integer - id da categoria.
	 * @return categoriaObj.
	 */
	public Categoria update( Categoria categoriaObj ) {
		
		Categoria newObj = find(categoriaObj.getId());
		
		updateData(newObj, categoriaObj);
		
		return categoriaRepository.save( newObj );
	}
	/**
	 * Metodo para excluir uma categoria.
	 * @param id - Integer - id da categoria.
	 */
	public void delete(Integer id) {
		
		find(id);
		
		try {
			categoriaRepository.deleteById(id);
			
			
		} catch (DataIntegrityViolationException e) {
			
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos");
		}
	}
	
	/**
	 * Metodo para buscar todas as categorias.
	 */
	public List<Categoria> findAll() {
		
		return categoriaRepository.findAll();
		
	}
	
	/**
	 * Metodo para paginação.
	 * @param page - Integwe - quantidade de paginas por paginação.
	 * @param linesPerPage - Integer - quantidade de linhas por pagina.
	 * @param orderBy - String - inidica a ordenaçãop (ordenado por).
	 * @param direction - String - indica a direção (ascendente ou descendente).
	 * @return pageRequest.
	 */
	public Page<Categoria> findPage( Integer page, Integer linesPerPage, String orderBy, String direction ) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy); 
		
		return categoriaRepository.findAll(pageRequest);
	}
	
	/**
	 * Metodo para converter para DTO.
	 * @param objetoDTO - CategoriaDTO.
	 * @return Categoria object.
	 */
	public Categoria convertFromDTO( CategoriaDTO objetoDTO) {
		
		return new Categoria(objetoDTO.getId(), objetoDTO.getNomeCategoria());
	}
	
	/**
	 * Metodo para atualizar os dados a partir de um novo obj.
	 * @param newObj - novo obj DTO.
	 * @param obj - Categoria - object.
	 */
	private void updateData(Categoria newObj, Categoria categoriaObj) {
		
		newObj.setNomeCategoria(categoriaObj.getNomeCategoria());
		
	}

}
