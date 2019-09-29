package com.vinicius.springboot.mc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.vinicius.springboot.mc.model.Categoria;
import com.vinicius.springboot.mc.model.Produto;
import com.vinicius.springboot.mc.repositories.CategoriaRepository;
import com.vinicius.springboot.mc.repositories.ProdutoRepository;
import com.vinicius.springboot.mc.services.exception.ObjectNotFoundException;

/**
 * Service responsável por nossas operações de CRUD.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	/**
	 * Metodo para buscar pelo id do pedido.
	 * @param id - Integer - id do pedido.
	 * @return Produto.
	 */		
	public Produto buscar( Integer id )  {
		
		Optional<Produto> produtoObj = produtoRepository.findById(id);
		return produtoObj.orElseThrow(() -> new ObjectNotFoundException (
				"Produto não encontrado! Identificador: " + id + " Tipo do objeto: " + Produto.class.getName()));
	}
	
	/**
	 * Metodo para implementar uma busca de produto por id.
	 * @param nome - String - nome do produto.
	 * @param ids - List<Integer> ids.
	 * @return produtoRepository.search( nome, categorias, pageResquest );
	 */
	public Page<Produto> search(String nomeProduto, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction ) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy); 
		
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		
		return produtoRepository.search( nomeProduto, categorias, pageRequest);
		
	}
	
}
