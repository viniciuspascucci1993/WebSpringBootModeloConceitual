 package com.vinicius.springboot.mc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe main extendendo CommandLineRunner para instanciarmos nossos modelos e adcionar em uma lista.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@SpringBootApplication
public class WebSpringBootComercialMcApplication implements CommandLineRunner{
	
	public static void main(String[] args) {
		SpringApplication.run(WebSpringBootComercialMcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	
	}

}
 	