 package com.vinicius.springboot.mc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vinicius.springboot.mc.services.S3Service;

/**
 * Classe main extendendo CommandLineRunner para instanciarmos nossos modelos e adcionar em uma lista.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@SpringBootApplication
public class WebSpringBootComercialMcApplication implements CommandLineRunner{
	
	@Autowired
	private S3Service s3Service;
	
	public static void main(String[] args) {
		SpringApplication.run(WebSpringBootComercialMcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		s3Service.uploadFile("E:\\temp\\fotos\\img07.jpg");
	}

}
 	