package com.vinicius.springboot.mc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.vinicius.springboot.mc.model.Cidade;

/**
 * Interface CidadeRepository responsável por anotar nossas operações de CRUD ( acessar banco de dados, consultas, etc ).
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{ 
	
	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Cidade obj WHERE obj.estado.id = :estadoId ORDER BY obj.nomeCidade")
	public List<Cidade> findCidades( @Param("estadoId") Integer estado_id );
}
