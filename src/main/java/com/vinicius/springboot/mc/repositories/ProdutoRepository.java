package com.vinicius.springboot.mc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.vinicius.springboot.mc.model.Categoria;
import com.vinicius.springboot.mc.model.Produto;

/**
 * Interface ProdutoRepository responsável por anotar nossas operações de CRUD ( acessar banco de dados, consultas, etc ).
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
	
	@Transactional(readOnly = true)
	// Connsulta JPQL.
	@Query("SELECT DISTINCT p FROM Produto p INNER JOIN p.categorias c WHERE p.nomeProduto LIKE %:nomeProduto% AND c IN :caregorias")
	Page<Produto> search(@Param("nomeProduto") String nome, @Param("caregorias") List<Categoria> categorias, Pageable pageRequest);
	
	//Padrão de nomes Spring Data ( cria consulta automaticamente pelo nome do método )
	
//	Page<Produto> findDistinctByNomeProdutoContainingAndCategoriasIn(String nome, List<Categoria> categorias, Pageable pageRequest);
}
