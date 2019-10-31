package com.vinicius.springboot.mc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinicius.springboot.mc.model.Estado;
import com.vinicius.springboot.mc.repositories.EstadoRepository;

/**
 * Service responsável por nossas operações de CRUD.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;
	
	/**
	 * Metodo para listar todos os estados.
	 * @return esrEstadoRepository.findAllByOrderByNome();
	 */
	public List<Estado> findAll() {
		
		return estadoRepository.findAllByOrderByNomeEstado();
	}
}
