package com.vinicius.springboot.mc.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.vinicius.springboot.mc.model.enums.Perfil;

/**
 * Classe responsável por tratar nosos Usuario com o Spring Security.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
public class UserSpringSecurity implements UserDetails{

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identificador.
	 */
	private Integer id;
	
	/**
	 * E-mail.
	 */
	private String email;
	
	/**
	 * Senha.
	 */
	private String senha;

	/**
	 * Authorities.
	 */
	private Collection<? extends GrantedAuthority> authorities;
	
	/**
	 * Construtor vazio
	 */
	public UserSpringSecurity() {  }
	
	/**
	 * Construtor com argumentos.
	 * @param id - Integer - Identificador.
	 * @param email - String - e-mail.
	 * @param senha - String - senha.
	 * @param authorities - Set<Perfil> - perfis..
	 */
	public UserSpringSecurity(Integer id, String email, String senha,
			Set<Perfil> perfis) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
	}



	/**
	 * Metodo get().
	 * @return id - Integer - identificador.
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * Metodo que retorna um GrantedAuthorities.
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	/**
	 * Metogo getPassword().
	 */
	@Override
	public String getPassword() {
		return this.senha;
	}

	/**
	 * Metodo getUsername().
	 * @return this.email.
	 */
	@Override
	public String getUsername() {
		return this.email;
	}

	/**
	 * Metodo boleano que verifica se a conta esta expirada.
	 * @return true.
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * Metodo boleano que verifica se a conta não esta travada.
	 * @return true.
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * Metodo boleano que verifica se as credenciais não estão expiradas.
	 * @return true.
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * Metodo boleano que verifica se esta habilitado ( usuario, login...).
	 * @return true.
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	// Metodo para verificar se o usuario possui permissão de ADMIN 
	/**
	 * Metodo para verificar se o usuario tem permissão de ADMIN.
	 * @param perfil - Perfil - Enum.
	 * @return getAuthorities().contains(new SimpleGrantedAuthority(perfil.getDescricao().
	 */
	public boolean hasPermission( Perfil perfil) {
		return getAuthorities().contains(new SimpleGrantedAuthority(perfil.getDescricao()));
	}

}
