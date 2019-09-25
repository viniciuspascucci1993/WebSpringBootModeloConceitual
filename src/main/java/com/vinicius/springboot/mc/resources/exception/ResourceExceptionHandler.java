package com.vinicius.springboot.mc.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.vinicius.springboot.mc.services.exception.ObjectNotFoundException;

/**
 * Classe handler que representa a interceptação caos ocorra uma exceção o handler que vai lançar a resposta HTTP adequada.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@ControllerAdvice
public class ResourceExceptionHandler {
	
	/**
	 * Método responsável por tratar uma mensagem adequada para caso a requisição seja mal sucedida.
	 * @param e - mensagem do erro.
	 * @param request - requisição HTTP.
	 * @return ResponseEntity.
	 */
	@ExceptionHandler( ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		
		StandardError erro = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis()); 
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}

}
