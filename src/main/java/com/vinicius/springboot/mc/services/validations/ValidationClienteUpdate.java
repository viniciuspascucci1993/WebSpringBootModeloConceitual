package com.vinicius.springboot.mc.services.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Anotação customizada para anotar no topo da nossa classe de modelo para validações como CPF e/ou CNPJ na atualização do cliente.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 *
 */
@Constraint(validatedBy = ValidatiorClienteUpdate.class) 
@Target({ ElementType.TYPE }) 
@Retention(RetentionPolicy.RUNTIME) 
public @interface ValidationClienteUpdate {

	String message() default "Erro de validação"; 
	
	Class<?>[] groups() default { };
	
	Class<? extends Payload>[] payload() default { };
}
