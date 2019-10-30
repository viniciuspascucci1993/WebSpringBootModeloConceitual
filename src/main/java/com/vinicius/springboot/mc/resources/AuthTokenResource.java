package com.vinicius.springboot.mc.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vinicius.springboot.mc.dto.AuxEmailDTO;
import com.vinicius.springboot.mc.security.UserSpringSecurity;
import com.vinicius.springboot.mc.security.util.JWTUtil;
import com.vinicius.springboot.mc.services.AuthService;
import com.vinicius.springboot.mc.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Classe responsável por utilizar um método de refresh token ( tempo de expiração)
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Api(value = "API REST modelo conceitual e estudo de caso - auth/forgot")
@RestController
@RequestMapping(value = "/auth")
public class AuthTokenResource {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService service;

	/**
	 * Metodo POST para requisições de refresh token ( novo token)
	 * @param response
	 * @return ResponseEntity.noContent
	 */
	@ApiOperation(value = "Metodo POST para requisições de refresh token ( novo token) - Refresh Token", code = 204)
	@RequestMapping( produces = "application/json", value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		
		UserSpringSecurity user = UserService.getUserLogado();
		
		String token = jwtUtil.generateToken(user.getUsername());
		
		response.addHeader("Authorization", "Bearer " + token);
		
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Metodo POST para esqueci minha Senha.
	 * @param response
	 * @return 
	 */
	@ApiOperation(value = "Metodo POST para esqueci minha Senha. - Forgot password", code = 204)
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ResponseEntity<Void> forgot( @Valid @RequestBody AuxEmailDTO objDto ) {
				
		service.sendNewPassword(objDto.getEmail());
		
		return ResponseEntity.noContent().build();
	}

}
