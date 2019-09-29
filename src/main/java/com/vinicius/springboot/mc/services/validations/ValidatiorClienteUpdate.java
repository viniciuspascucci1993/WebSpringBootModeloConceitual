package com.vinicius.springboot.mc.services.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.vinicius.springboot.mc.dto.ClienteDTO;
import com.vinicius.springboot.mc.model.Cliente;
import com.vinicius.springboot.mc.repositories.ClienteRepository;
import com.vinicius.springboot.mc.resources.exception.FieldMessage;

/**
 * Classe responsável por nosso validador de cliente insert.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
public class ValidatiorClienteUpdate implements ConstraintValidator<ValidationClienteUpdate, ClienteDTO>{
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize( ValidationClienteUpdate nome ) { }
	
	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		
		// Dessa forma pegamos o URI do endPoint. Ex cliente/1
		@SuppressWarnings("unchecked")
		Map<String, String> map = ( Map<String, String> ) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> lista = new ArrayList<FieldMessage>();
		
		// Aqui iniciará as validações
		// Valida E-mail repetido na atualização do cliente.
		Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
		
		if (aux != null && !aux.getId().equals(uriId)) {
			lista.add(new FieldMessage("email", "Este e-mail ja está sendo usado por outro usuario."));
		}
		
		// for para percorrer a lista correspondente e adcionar os erros nela.
		for (FieldMessage fieldMessage : lista) {
			
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(fieldMessage.getMessage()).addPropertyNode(fieldMessage.getFieldName())
						.addConstraintViolation();
		}
		return lista.isEmpty();
	}
	
	

}
