package com.vinicius.springboot.mc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinicius.springboot.mc.model.Estado;

/**
 * Interface EstadoRepository responsável por anotar nossas operações de CRUD ( acessar banco de dados, consultas, etc ).
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
public interface EstadoRepository extends JpaRepository<Estado, Integer>{ }
