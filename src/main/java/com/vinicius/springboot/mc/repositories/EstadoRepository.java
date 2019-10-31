package com.vinicius.springboot.mc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vinicius.springboot.mc.model.Estado;

/**
 * Interface EstadoRepository responsável por anotar nossas operações de CRUD ( acessar banco de dados, consultas, etc ).
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{ 
	
	@Transactional(readOnly=true)
	public List<Estado> findAllByOrderByNomeEstado();
}
