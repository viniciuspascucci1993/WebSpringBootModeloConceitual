package com.vinicius.springboot.mc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinicius.springboot.mc.model.Cliente;
import com.vinicius.springboot.mc.repositories.ClienteRepository;
import com.vinicius.springboot.mc.services.exception.ObjectNotFoundException;

/**
 * Service responsável por nossas operações de CRUD.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente buscar( Integer id )  {
		
		Optional<Cliente> clienteObj = clienteRepository.findById(id);
		return clienteObj.orElseThrow(() -> new ObjectNotFoundException (
				"Cliente não encontrado! Identificador: " + id + " Tipo do objeto: " + Cliente.class.getName()));
	}
}
