package com.vinicius.springboot.mc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinicius.springboot.mc.model.Cidade;
import com.vinicius.springboot.mc.repositories.CidadeRepository;

/**
 * Service responsável por nossas operações de CRUD.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	/**
	 * Metodo para encontrar cidades por estado9.
	 * @param estadoId - Integer - id do estado.
	 * @return cidadeRepository.findCidades(estadoId);
	 */
	public List<Cidade> findByEstados( Integer estadoId ) {
		
		return cidadeRepository.findCidades(estadoId);
	}
}
