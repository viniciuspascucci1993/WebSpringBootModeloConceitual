package com.vinicius.springboot.mc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vinicius.springboot.mc.dto.ClienteDTO;
import com.vinicius.springboot.mc.dto.ClienteNewDTO;
import com.vinicius.springboot.mc.model.Cidade;
import com.vinicius.springboot.mc.model.Cliente;
import com.vinicius.springboot.mc.model.Endereco;
import com.vinicius.springboot.mc.model.enums.TipoCliente;
import com.vinicius.springboot.mc.repositories.ClienteRepository;
import com.vinicius.springboot.mc.repositories.EnderecoRepository;
import com.vinicius.springboot.mc.services.exception.DataIntegrityException;
import com.vinicius.springboot.mc.services.exception.ObjectNotFoundException;

/**
 * Service responsável por nossas operações de CRUD.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Cliente find( Integer id )  {
		
		Optional<Cliente> clienteObj = clienteRepository.findById(id);
		return clienteObj.orElseThrow(() -> new ObjectNotFoundException (
				"Cliente não encontrado! Identificador: " + id + " Tipo do objeto: " + Cliente.class.getName()));
	}
	
	/**
	 * Metodo para inserir um cliente..
	 * @param id - Integer - id do cliente.
	 * @return clienteObj.
	 */
	@Transactional
	public Cliente insert( Cliente clienteObj ) {
		
		clienteObj.setId( null );
		
		clienteObj = clienteRepository.save( clienteObj );
		
		enderecoRepository.saveAll(clienteObj.getEnderecos());
		
		return clienteObj;
	}
	
	/**
	 * Metodo para atualizar um cliente.
	 * @param id - Integer - id do cliente..
	 * @return clienteObj.
	 */
	public Cliente update( Cliente clienteObj ) {
		
		Cliente newObj = find(clienteObj.getId());
		
		updateData(newObj, clienteObj);
		
		return clienteRepository.save( newObj );
	}
	
	/**
	 * Metodo para excluir um cliente.
	 * @param id - Integer - id do cliente..
	 */
	public void excluir(Integer id) {
		
		find(id);
		
		try {
			clienteRepository.deleteById(id);
			
			
		} catch (DataIntegrityViolationException e) {
			
			throw new DataIntegrityException("Não é possivel excluir um cliente pois há pedidos relacionados");
		}
	}
	
	/**
	 * Metodo para buscar todos os clientes.
	 */
	public List<Cliente> findAll() {
		
		return clienteRepository.findAll();
		
	}
	
	/**
	 * Metodo para paginação.
	 * @param page - Integwe - quantidade de paginas por paginação.
	 * @param linesPerPage - Integer - quantidade de linhas por pagina.
	 * @param orderBy - String - inidica a ordenaçãop (ordenado por).
	 * @param direction - String - indica a direção (ascendente ou descendente).
	 * @return pageRequest.
	 */
	public Page<Cliente> findPage( Integer page, Integer linesPerPage, String orderBy, String direction) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy); 
		
		return clienteRepository.findAll(pageRequest);
	}
	
	/**
	 * Metodo para converter para DTO.
	 * @param objetoDTO - CategoriaDTO.
	 * @return Cliente object.
	 */
	public Cliente convertFromDTO( ClienteDTO objetoDTO) {
		
		return new Cliente(objetoDTO.getId(), objetoDTO.getNomeCliente(), objetoDTO.getEmail(), null, null);
	}
	
	/**
	 * Metodo para converter para newDTO.
	 * @param objetoDTO - ClienteNewDTO.
	 * @return Cliente object.
	 */
	public Cliente convertFromDTO( ClienteNewDTO objetoDTO) {
		
		Cliente cliente = new Cliente(null, objetoDTO.getNomeCliente(), objetoDTO.getEmail(), objetoDTO.getCpfCnpj(), TipoCliente.toEnum(objetoDTO.getTipoCliente()));
		
		Cidade cidade = new Cidade(objetoDTO.getCidadeId(), null, null );
		
		Endereco endereco = new Endereco(null, objetoDTO.getLogradouro(), objetoDTO.getNumero(), objetoDTO.getComplemento(), objetoDTO.getBairro(), objetoDTO.getCep(), cliente, cidade);
		
		cliente.getEnderecos().add(endereco);
		
		cliente.getTelefones().add(objetoDTO.getTelefoneResidencial());
		
		if (objetoDTO.getTelefoneComecial() != null) {
			cliente.getTelefones().add(objetoDTO.getTelefoneComecial());
		}
		
		if (objetoDTO.getCelular() != null) {
			cliente.getTelefones().add(objetoDTO.getCelular());
		}
		
		return cliente;
		 
	}
	
	/**
	 * Metodo para atualizar os dados a partir de um novo obj.
	 * @param newObj - novo obj DTO.
	 * @param obj - Cliente - object.
	 */
	private void updateData( Cliente newObj, Cliente obj) {
		
		newObj.setNomeCliente(obj.getNomeCliente());
		newObj.setEmail(obj.getEmail());
	}
}
