package com.vinicius.springboot.mc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vinicius.springboot.mc.dto.CategoriaDTO;
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
	 * @return ResponseEntity<Categoria>
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> buscarCategoria( @PathVariable Integer id ) {
		
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
	
	/**
	 * Metodo PUT para atualizar uma nova categoria.
	 * @param categoriaObj - Object - categoriaObj.
	 * @return ResponseEntity.created.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizarCategoria( @RequestBody Categoria categoriaObj, @PathVariable Integer id ) {
		
		categoriaObj.setId( id );
		
		categoriaObj = categoriaService.atualizar( categoriaObj );
		
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Metodo DELETE para excluir uma categoria.
	 * @param id - Integer - id da categoria.
	 * @return ResponseEntity<Void>
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluirCategoria( @PathVariable Integer id ) {
		
		categoriaService.excluir(id);
		
		return ResponseEntity.noContent().build();
	}
	

	/**
	 * Metodo GET para listar todas as categorias.
	 * @return ResponseEntity<Categoria>
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> buscarTodasCategorias() {
		
		List<Categoria> lista = categoriaService.buscarTodasCategorias();
		
		List<CategoriaDTO> listaDto = lista.stream()
					.map(categoriaObj -> new CategoriaDTO(categoriaObj)).collect(Collectors.toList()); // Assim convertemos uma lista para outta lista
		
		return ResponseEntity.ok().body(listaDto);
	}
}
