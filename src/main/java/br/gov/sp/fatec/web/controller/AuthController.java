package br.gov.sp.fatec.web.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.gov.sp.fatec.model.User;
import br.gov.sp.fatec.sercurity.JwtUtils;
import br.gov.sp.fatec.sercurity.Login;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager auth;

	public void setAuth(AuthenticationManager auth) {
		this.auth = auth;
	}

	@RequestMapping(path = "/sing-in", method = RequestMethod.POST)
	public ResponseEntity<User> login(@RequestBody Login login, HttpServletResponse response)
			throws JsonProcessingException {
		Authentication credentials = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
		User usuario = (User) auth.authenticate(credentials).getPrincipal();
		response.setHeader("Token", JwtUtils.generateToken(usuario));
		return new ResponseEntity<User>(usuario, HttpStatus.OK);
	}

}
