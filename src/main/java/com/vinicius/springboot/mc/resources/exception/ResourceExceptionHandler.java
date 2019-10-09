package com.vinicius.springboot.mc.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.vinicius.springboot.mc.services.exception.AuthorizationException;
import com.vinicius.springboot.mc.services.exception.DataIntegrityException;
import com.vinicius.springboot.mc.services.exception.ObjectNotFoundException;

/**
 * Classe handler que representa a interceptação caos ocorra uma exceção o handler que vai lançar a resposta HTTP adequada.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@ControllerAdvice
public class ResourceExceptionHandler {
	
	/**
	 * Método responsável por tratar uma mensagem adequada para caso não seja encontrado o objeto.
	 * @param e - mensagem do erro.
	 * @param request - requisição HTTP.
	 * @return ResponseEntity.
	 */
	@ExceptionHandler( AuthorizationException.class)
	public ResponseEntity<StandardError> authorizationException(AuthorizationException e, HttpServletRequest request) {
		
		StandardError erro = new StandardError(HttpStatus.FORBIDDEN.value(), e.getMessage(), System.currentTimeMillis()); 
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(erro);
	}
	
	/**
	 * Método responsável por tratar uma mensagem adequada para caso não seja encontrado o objeto.
	 * @param e - mensagem do erro.
	 * @param request - requisição HTTP.
	 * @return ResponseEntity.
	 */
	@ExceptionHandler( ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		
		StandardError erro = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis()); 
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	/**
	 * Método responsável por tratar uma mensagem adequada para caso a requisição seja mal sucedida.
	 * @param e - mensagem do erro.
	 * @param request - requisição HTTP.
	 * @return ResponseEntity.
	 */	
	@ExceptionHandler( DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
		
		StandardError erro = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis()); 
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	/**
	 * Método responsável por tratar uma mensagem adequada para caso a requisição seja mal sucedida.
	 * @param e - mensagem do erro.
	 * @param request - requisição HTTP.
	 * @return ResponseEntity.
	 */	
	@ExceptionHandler( MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validationError(MethodArgumentNotValidException e, HttpServletRequest request) {
		
		ValidationError erro = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validação", System.currentTimeMillis()); 
		
		// Percorrer a lista de erros para ser mostrada na nossa exceção.
		for( FieldError error : e.getBindingResult().getFieldErrors() ) {
			erro.addError(error.getField(), error.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

}
