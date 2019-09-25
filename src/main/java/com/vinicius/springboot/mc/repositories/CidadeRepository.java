package com.vinicius.springboot.mc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinicius.springboot.mc.model.Cidade;

/**
 * Interface CidadeRepository responsável por anotar nossas operações de CRUD ( acessar banco de dados, consultas, etc ).
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{ }
