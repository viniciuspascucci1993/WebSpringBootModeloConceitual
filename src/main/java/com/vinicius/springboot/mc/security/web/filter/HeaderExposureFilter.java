package com.vinicius.springboot.mc.security.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

/**
 * Filtro responsável por expor o location no cabeçalho da requisilção.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Component
public class HeaderExposureFilter implements Filter {

	/**
	 * Metodo void init().
	 */
	@Override
	public void init( FilterConfig filterConfig ) throws ServletException {
		
	}
	
	
	/**
	 * Metodo void doFilter().
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletResponse res = ( HttpServletResponse ) response; 
		
		res.addHeader("access-control-expose-headers", "location");
		
		chain.doFilter(request, response);
	}
	
	/**
	 * Metodo void destroy().
	 */
	@Override
	public void destroy() { }

}
