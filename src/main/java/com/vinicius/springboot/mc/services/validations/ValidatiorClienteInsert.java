package com.vinicius.springboot.mc.services.validations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.vinicius.springboot.mc.dto.ClienteNewDTO;
import com.vinicius.springboot.mc.model.enums.TipoCliente;
import com.vinicius.springboot.mc.resources.exception.FieldMessage;
import com.vinicius.springboot.mc.validation.util.CpfCnpjUtil;

/**
 * Classe responsável por nosso validador de cliente insert.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
public class ValidatiorClienteInsert implements ConstraintValidator<ValidationClienteInsert, ClienteNewDTO>{

	@Override
	public void initialize( ValidationClienteInsert nome ) { }
	
	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> lista = new ArrayList<FieldMessage>();
		
		// Aqui iniciará as validações
		if (objDto.getTipoCliente().equals(TipoCliente.PESSOA_FISICA.getCodigo()) && !CpfCnpjUtil.isValidCpf(objDto.getCpfCnpj())) {
			lista.add(new FieldMessage("cpfCnpj", "CPF Inválido"));
		}
		
		if (objDto.getTipoCliente().equals(TipoCliente.PESSOA_JURIDICA.getCodigo()) && !CpfCnpjUtil.isValidCnpj(objDto.getCpfCnpj())) {
			lista.add(new FieldMessage("cpfCnpj", "CNPJ Inválido"));
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
