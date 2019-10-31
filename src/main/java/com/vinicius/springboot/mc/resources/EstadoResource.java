 package com.vinicius.springboot.mc.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vinicius.springboot.mc.dto.CidadeDTO;
import com.vinicius.springboot.mc.dto.EstadoDTO;
import com.vinicius.springboot.mc.model.Cidade;
import com.vinicius.springboot.mc.model.Estado;
import com.vinicius.springboot.mc.services.CidadeService;
import com.vinicius.springboot.mc.services.EstadoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Classe EstadoResource que representa os nossos serviços REST.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Api(value = "API REST modelo conceitual e estudo de caso - Model Estado")
@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private CidadeService cidadeService;
	
	/**
	 * Metodo GET para requisições de consulta.
	 * @param id - Integer - id do estado.
	 * @return ResponseEntity<Estado>
	 */ 
	@ApiOperation(value = "Metodo GET para requisições de consulta.", code = 200)
	@RequestMapping( produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> findAll() {
		
		List<Estado> lista = estadoService.findAll();
		
		List<EstadoDTO> listaDto = lista.stream().map(estadoObj -> new EstadoDTO(estadoObj)).collect(Collectors.toList()); // Assim convertemos uma lista para outta lista
		
		return ResponseEntity.ok().body(listaDto);
	}
	
	/**
	 * Metodo GET para requisições de consulta passando um estado como parametro para buscar cidades.
	 * @param estadoId - Integer - id do estado.
	 * @return ResponseEntity.ok().body(listaDto);
	 */
	@ApiOperation(value = "Metodo GET para requisições de consulta passando um estado como parametro para buscar cidades.", code = 200)
	@RequestMapping( produces = "application/json", value = "/{estadoId}/cidades", method = RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> findCidades( @PathVariable Integer estadoId ) {
		
		List<Cidade> lista = cidadeService.findByEstados(estadoId);
		
		List<CidadeDTO> listaDto = lista.stream().map(cidadeObj -> new CidadeDTO(cidadeObj)).collect(Collectors.toList()); // Assim convertemos uma lista para outta lista
		
		return ResponseEntity.ok().body(listaDto);
	}
}
