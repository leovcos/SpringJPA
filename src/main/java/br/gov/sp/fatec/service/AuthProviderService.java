package br.gov.sp.fatec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.model.User;
import br.gov.sp.fatec.repository.UserRepository;

@Service
public class AuthProviderService implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String login = auth.getName();
        Object password = auth.getCredentials();
        
        if (password == null) {
            throw new UsernameNotFoundException("Login e/ou Senha inválidos.");
        }

        User user = userRepository.findByUsername(login);
        
        if (user != null && user.getPassword().equals(UserServiceImpl.md5((String) password))) {
        	return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        }
        
        throw new UsernameNotFoundException("Login e/ou Senha inválidos.");
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}