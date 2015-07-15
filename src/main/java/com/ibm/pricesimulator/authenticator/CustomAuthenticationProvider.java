package com.ibm.pricesimulator.authenticator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.ibm.pricesimulator.service.UserService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
 
	@Autowired
	private UserService userService;
 
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
 
    	try {
			userService.doAuthentication(username, password);
		} catch (Exception e) {
			throw new BadCredentialsException(e.getMessage());
		}
 
        return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), password, createRole());
    }
    
    public Collection<GrantedAuthority> createRole() {
    	List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    	authorities.add(new SimpleGrantedAuthority("ROLE_ACCESS"));
    	
        return authorities;
    }
 
    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }
}
