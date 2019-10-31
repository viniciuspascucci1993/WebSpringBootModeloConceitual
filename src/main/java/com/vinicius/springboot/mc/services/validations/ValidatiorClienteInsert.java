package com.vinicius.springboot.mc.services.validations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.vinicius.springboot.mc.dto.ClienteNewDTO;
import com.vinicius.springboot.mc.model.Cliente;
import com.vinicius.springboot.mc.model.enums.TipoCliente;
import com.vinicius.springboot.mc.repositories.ClienteRepository;
import com.vinicius.springboot.mc.resources.exception.FieldMessage;
import com.vinicius.springboot.mc.validation.util.CpfCnpjUtil;

/**
 * Classe responsável por nosso validador de cliente insert.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
public class ValidatiorClienteInsert implements ConstraintValidator<ValidationClienteInsert, ClienteNewDTO>{
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void initialize( ValidationClienteInsert nome ) { }
	
	/**
	 * Metodo isValid para checagem de CPF/CNPJ Inválido e email inválido no momento de uma inserção
	 * @return List<FieldMessage>.
	 */
	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> lista = new ArrayList<FieldMessage>();
		
		// Aqui iniciará as validações
		// Valida CPF
		if (objDto.getTipoCliente().equals(TipoCliente.PESSOA_FISICA.getCodigo()) && !CpfCnpjUtil.isValidCpf(objDto.getCpfCnpj())) {
			lista.add(new FieldMessage("cpfCnpj", "CPF Inválido"));
		}
		
		// Valida CNPJ
		if (objDto.getTipoCliente().equals(TipoCliente.PESSOA_JURIDICA.getCodigo()) && !CpfCnpjUtil.isValidCnpj(objDto.getCpfCnpj())) {
			lista.add(new FieldMessage("cpfCnpj", "CNPJ Inválido"));
		}
		
		// Valida E-mail repetido na inserção do cliente.
		Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
		
		if (aux != null) {
			lista.add(new FieldMessage("email", "O e-mail que você informou ja existe."));
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
