package com.vinicius.springboot.mc.security.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.vinicius.springboot.mc.security.util.JWTUtil;

/**
 * Classe responsável por filtrar nossas requisições de autorização com JWT Token.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter{

	private JWTUtil jwtUtil;
	
	private UserDetailsService userDetailsService;
	
	/**
	 * Construtor com argumentos.
	 * @param authenticationManager - AuthenticationManager - authentication.
	 * @param jwtUtil - JwtUtil - Classe Utilitária.
	 * @param userDetailsService - UserDetailService - detalhes do usuario.
	 */
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}
	
	/**
	 * Metodo doFilterInternal().
	 * Responsável por realizar um filtro internal fornecendo no header da requisição o Authorization, após isso como valor fornece Bearer e o token adiquirido na resposta.
	 */
	@Override
	protected void doFilterInternal( HttpServletRequest request,
									 HttpServletResponse response,
									 FilterChain chain ) throws IOException, ServletException {
		
		String auth = request.getHeader( "Authorization" );
		
		if (auth != null && auth.startsWith("Bearer ")) {
			UsernamePasswordAuthenticationToken authToken = getAuthentication(auth.substring(7));
			
			if (authToken != null) {
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * Metodo para capturar a authentication do usuario.
	 * @param token - String - token.
	 * @return UsernamePasswordAuthenticationToken.
	 */
	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		
		if (jwtUtil.tokenValido(token)) {
			
			String username = jwtUtil.getUsername(token);
			
			UserDetails users = userDetailsService.loadUserByUsername(username); 
			
			return new UsernamePasswordAuthenticationToken(users, null, users.getAuthorities());
		}
		
		return null;
	}

}
