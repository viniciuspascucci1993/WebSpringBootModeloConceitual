package com.vinicius.springboot.mc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinicius.springboot.mc.model.Cliente;

/**
 * Interface ClienteRepository responsável por anotar nossas operações de CRUD ( acessar banco de dados, consultas, etc ).
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{ }
