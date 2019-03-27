package br.gov.sp.fatec.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;

import br.gov.sp.fatec.model.User;
import br.gov.sp.fatec.model.User;
import br.gov.sp.fatec.security.JwtUtils;
import br.gov.sp.fatec.security.Login;
import br.gov.sp.fatec.service.UserServiceImpl;
import br.gov.sp.fatec.view.View;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager auth;

	@Autowired
	private UserServiceImpl userService;

	public void setAuth(AuthenticationManager auth) {
		this.auth = auth;
	}

	@RequestMapping(path = "/register", method = RequestMethod.POST)
	@JsonView(View.UserShort.class)
	public ResponseEntity<?> save(@RequestBody Login login, HttpServletRequest request, HttpServletResponse response) {
		User user = new User();
		user.setUsername(login.getUsername());
		user.setPassword(login.getPassword());

		try {
			user = userService.save(user);			
		} catch (Exception e) {
			Map<String,String> errorResponse = new HashMap<String, String>();
			errorResponse.put("message", e.getMessage());
			return new ResponseEntity<Map<String,String>>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
    
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	@JsonView(View.UserShort.class)
    public ResponseEntity<User> login(@RequestBody Login login, HttpServletResponse response) throws JsonProcessingException {        
        UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
        		login.getUsername(), login.getPassword());
        
        User user = (User) auth.authenticate(credentials).getPrincipal();
        response.setHeader("Token", JwtUtils.generateToken(user));
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

}
