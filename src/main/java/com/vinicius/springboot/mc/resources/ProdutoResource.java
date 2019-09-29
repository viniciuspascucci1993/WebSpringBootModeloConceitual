package com.vinicius.springboot.mc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vinicius.springboot.mc.dto.ProdutoDTO;
import com.vinicius.springboot.mc.model.Produto;
import com.vinicius.springboot.mc.resources.utils.UrlAuxiliarUtil;
import com.vinicius.springboot.mc.services.ProdutoService;

/**
 * Classe ProdutoResource que representa os nossos serviços REST.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;
	
	/**
	 * Metodo GET para requisições de consulta
	 * @param id - Integer - id do produto.
	 * @return ResponseEntity<Produto>
	 */	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> buscarProduto( @PathVariable Integer id ) {
		
		Produto produtoiaObj = service.find(id);
		
		return ResponseEntity.ok().body(produtoiaObj);
		
	}
	
	/**
	 * Metodo GET para paginação das categorias.
	 * @return ResponseEntity.ok().body(listaDto).
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> encontrarCategoriasPorPaginacao(
			@RequestParam(value = "nomeProduto", defaultValue = "") String nomeProduto,
			@RequestParam(value = "categorias", defaultValue = "") String categorias,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nomeProduto") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		
		String nomeDecoded = UrlAuxiliarUtil.decodeParam(nomeProduto);
		
		List<Integer> ids = UrlAuxiliarUtil.decodeList(categorias);
		
		Page<Produto> lista = service.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		
		Page<ProdutoDTO> listaDto = lista.map(produtoObj -> new ProdutoDTO(produtoObj)); // Assim convertemos uma lista para outta lista
		
		return ResponseEntity.ok().body(listaDto);
	}
}
