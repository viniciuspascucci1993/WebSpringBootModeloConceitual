package com.vinicius.springboot.mc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vinicius.springboot.mc.model.enums.TipoCliente;

/**
 * Classe responsável por identificar os atributos de um cliente.
 * @author Vinicius-PC - Vinicius Torres Pascucci,
 */
@Entity
public class Cliente implements Serializable{

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Representa o identificador do cliente. 
	 */
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/**
	 * Representa o nome do cliente
	 */
	private String nomeCliente;
	
	/**
	 * Representa o e-mail do cliente.
	 */
	private String email;
	
	/**
	 * Representa o CPF e/ou CNPJ do cliente
	 */
	private String cpfCnpj;
	
	/**
	 * Representa uma lista de endereços.
	 */
	@OneToMany( mappedBy = "cliente")
	private List<Endereco> enderecos = new ArrayList<Endereco>();

	/**
	 * Representa os telefones através de um conjunto de Strings.
	 */
	@ElementCollection
	@CollectionTable( name = "TELEFONE")
	private Set<String> telefones = new HashSet<String>();
	
	/**
	 * Representa uma lista de pedidos.
	 */
	@JsonIgnore
	@OneToMany( mappedBy = "cliente")
	private List<Pedido> pedidos = new ArrayList<Pedido>();
	
	/**
	 * Representa o tipo enumerado do cliente.
	 */
	private Integer tipoCliente;
	
	/**
	 * Construtor vazio.
	 */
	public Cliente() { }

	/**
	 * Consturtor com argumentos.
	 * @param id - Integer - id.
	 * @param nomeCliente - String - nome do cliente.
	 * @param email - String - e-mail do cliente.
	 * @param cpfCnpj - CPF e/ou CNPJ do cliente.
	 * @param tipoCliente - TipoCliente - tipo do cliente.
	 */
	public Cliente(Integer id, String nomeCliente, String email, String cpfCnpj, TipoCliente tipoCliente) {
		super();
		this.id = id;
		this.nomeCliente = nomeCliente;
		this.email = email;
		this.cpfCnpj = cpfCnpj;
		this.tipoCliente = tipoCliente.getCodigo();
	}
	
	/**
	 * Metodo get().
	 * @return id - Integer - identificador cliente.
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Metodo set().
	 * @param id -  - Integer - identificador cliente.
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * Metodo get().
	 * @return nomeCliente - String - nome do cliente.
	 */
	public String getNomeCliente() {
		return this.nomeCliente;
	}

	/**
	 * Metodo set().
	 * @param nomeCliente - String - nome do cliente.
	 */
	public void setNomeCliente(final String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	/**
	 * Metodo get().
	 * @return email - String - e-mail do cliente.
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Metodo set().
	 * @param email - String - e-mail do cliente.
	 */
	public void setEmail(final String email) {
		this.email = email;
	}

	/**
	 * Metodo get().
	 * @return cpfCnpj - String - CPF e/ou CNPJ do cliente.
	 */
	public String getCpfCnpj() {
		return this.cpfCnpj;
	}

	/**
	 * Metodo set().
	 * @param cpfCnpj - String - CPF e/ou CNPJ do cliente.
	 */
	public void setCpfCnpj(final String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	/**
	 * Metodo get().
	 * @return enderecos - List<Endereco> - lista de endereços.
	 */
	public List<Endereco> getEnderecos() {
		return this.enderecos;
	}

	/**
	 * Metodo set().
	 * @param enderecos - List<Endereco> - lista de endereços.
	 */
	public void setEnderecos(final List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	/**
	 * Metodo get().
	 * @return telefones - Set<String> - telefones do cliente.
	 */
	public Set<String> getTelefones() {
		return this.telefones;
	}

	/**
	 * Metodo set().
	 * @param telefones - Set<String> - telefones do cliente.
	 */
	public void setTelefones(final Set<String> telefones) {
		this.telefones = telefones;
	}
	
	/**
	 * Metodo get().
	 * @return pedidos - List<Pedido> - lista de pedidos.
	 */
	public List<Pedido> getPedidos() {
		return this.pedidos;
	}

	/**
	 * Metodo set().
	 * @param pedidos - List<Pedido> - lista de pedidos.
	 */
	@OneToMany( mappedBy = "cliente")
	public void setPedidos(final List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	/**
	 * Metodo get().
	 * @return tipoCliente - Object - tipo do cliente.
	 */
	public TipoCliente getTipoCliente() {
		return TipoCliente.toEnum(tipoCliente);
	}

	/**
	 * Metodo set().
	 * @param tipoCliente - Object - tipo do cliente.
	 */
	public void setTipoCliente(final TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente.getCodigo();
	}

	/**
	 * Metodo hashCode (Comparar objetos por valor)
	 * Faz uma comparação entre o id
	 */		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * Metodo equals (Comparar objetos por valor)
	 * Faz uma comparação entre o id
	 */		
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
