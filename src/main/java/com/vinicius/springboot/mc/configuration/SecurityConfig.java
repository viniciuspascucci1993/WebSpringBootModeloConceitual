package com.vinicius.springboot.mc.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.vinicius.springboot.mc.security.util.JWTUtil;
import com.vinicius.springboot.mc.security.web.filter.JWTAuthenticationFilter;
import com.vinicius.springboot.mc.security.web.filter.JWTAuthorizationFilter;

/**
 * Classe respon´savel por definir as nossas configurações de segurança.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) 
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailService;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	private static final String[] PERMISSIONS = { 
			"/h2-console/**"
	};
	
	private static final String[] PERMISSIONS_GET = { 
			"/produtos/**",
			"/categorias/**",
			"/relatorio/**",
			"/estados/**"
			
	};
	
	private static final String[] PERMISSIONS_POST = { 
			"/clientes",
			"/auth/forgot/**"
	};
	
	private static final String[] SWAGGER_DOCS_LIST = { 
            // -- swagger ui
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"	
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
			.antMatchers(HttpMethod.POST, SWAGGER_DOCS_LIST).permitAll()
			.antMatchers( HttpMethod.POST, PERMISSIONS_POST).permitAll()
			.antMatchers( HttpMethod.GET, PERMISSIONS_GET).permitAll()
			.antMatchers(HttpMethod.GET, SWAGGER_DOCS_LIST).permitAll()
			.antMatchers(PERMISSIONS).permitAll()
			.anyRequest().authenticated();
		security.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		security.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailService));
		security.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	/**
	 * Configuração para autenticação.
	 */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
	}
	
	/**
	 * Definido um bean de cors configuration para acessar por multiplas fontes para todos os caminhos.
	 * @return source.
	 */
	  @Bean
	  CorsConfigurationSource corsConfigurationSource() {
		  
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		
		source.registerCorsConfiguration("/**", configuration);
		
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
