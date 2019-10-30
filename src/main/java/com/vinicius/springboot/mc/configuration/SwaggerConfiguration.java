package com.vinicius.springboot.mc.configuration;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Classe responsável por gerar a documentação baseado em um módulo da aplicação.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	
	private static final String TITULO_SPRINGBOOT_MC = "Titulo - Modelo Conceitual com Spring Boot"; 
	
	private static final String DESCRICAO = "Descrição - Estudo de caso do modelo conceitual com Spring boot";
	
	private static final String VERSAO = "1,0";
	
	private static final String TERMOS = "Termos";
	
	private static final Contact CONTATO = new Contact("Vinicius Pascucci", 
					"https://github.com/viniciuspascucci1993/WebSpringBootModeloConceitual", 
					"vinicius.pascucci1@gmail.com");
	
	private static final String LICENSE_CODE =  "Apache License";
	
	private static final String URL_LICENSE = "Url";
	
	/**
	 * Metodo responsável por gerar nossa ddocumentação com SWAGGER beaseado em um pacote especifico da aplicação.
	 * @return new Docket();
	 */
	@Bean
	public Docket configApiDoc() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.vinicius.springboot.mc.resources"))
				.paths(PathSelectors.any())
				.build();
	}
	
	@SuppressWarnings("rawtypes")
	public ApiInfo infoDocumentation() {
		
		return new ApiInfo(TITULO_SPRINGBOOT_MC,
						   DESCRICAO, 
						   VERSAO, 
						   TERMOS, 
						   CONTATO, 
						   LICENSE_CODE, 
						   URL_LICENSE,
						   new ArrayList<VendorExtension>());
	}

}
