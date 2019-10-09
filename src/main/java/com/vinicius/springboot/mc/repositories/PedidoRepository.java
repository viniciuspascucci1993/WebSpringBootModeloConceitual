package com.vinicius.springboot.mc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vinicius.springboot.mc.model.Cliente;
import com.vinicius.springboot.mc.model.Pedido;

/**
 * Interface PedidoRepository responsável por anotar nossas operações de CRUD ( acessar banco de dados, consultas, etc ).
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
	
	@Transactional(readOnly = true)
	Page<Pedido> findByCliente( Cliente cliente, Pageable pageRequest);
}
