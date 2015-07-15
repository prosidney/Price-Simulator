package com.ibm.pricingsimulator.authenticator;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.ibm.pricingsimulator.service.UserService;

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
 
        return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), password, createRole("ROLE_ACCESS"));
    }
    
    public Collection<GrantedAuthority> createRole(String... roles) {
    	List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    	
    	for (String role : Arrays.asList(roles)) {
    		authorities.add(new SimpleGrantedAuthority(role));
		}
    	
        return authorities;
    }
 
    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }
}
