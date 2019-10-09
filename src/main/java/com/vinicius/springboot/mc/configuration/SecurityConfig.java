package com.vinicius.springboot.mc.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Classe respon´savel por definir as nossas configurações de segurança.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Autowired
	private Environment environment;
	
	private static final String[] PERMISSIONS = { 
			"/h2-console/**"
	};
	
	private static final String[] PERMISSIONS_GET = { 
			"/produtos/**",
			"/categorias/**",
			"/clientes/**"
	};
	

	
	/**
	 * Configuração para permitir nossos acessos a partir das nossas chaves de vetores.
	 */
	@Override
	protected void configure ( HttpSecurity security ) throws Exception {
		
		
		 //Condição para habilitar nosso banco de dados h2.
		if (Arrays.asList(environment.getActiveProfiles()).contains("test")) {
			security.headers().frameOptions().disable();
		}
		
		security.cors().and().csrf().disable();
		security.authorizeRequests()
			.antMatchers( HttpMethod.GET, PERMISSIONS_GET).permitAll()
			.antMatchers(PERMISSIONS).permitAll()
			.anyRequest().authenticated();
		security.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	/**
	 * Definido um bean de cors configuration para acessar por multiplas fontes para todos os caminhos.
	 * @return source.
	 */
	  @Bean
	  CorsConfigurationSource corsConfigurationSource() {
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
	    return source;
	  }
	  
	  /**
	   * Metodo para armazenar sen ha do cliente encodada.
	   * @return bCryptPasswordEncoder.
	   */
	  @Bean
	  public BCryptPasswordEncoder passwordEncoder() {
		  return new BCryptPasswordEncoder();
	  }
}
