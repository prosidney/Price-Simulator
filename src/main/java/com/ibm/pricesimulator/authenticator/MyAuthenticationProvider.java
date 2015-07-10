package com.ibm.pricesimulator.authenticator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Custom Spring Provider to generate the authentication logic 
 * 
 * @author sidsilva
 *
 */
public class MyAuthenticationProvider implements AuthenticationProvider,Serializable {
	private static final Logger log = Logger.getLogger(MyAuthenticationProvider.class);
	
/*	@Autowired
	private UserService userService;*/
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean supports ( Class<? extends Object> authentication ) {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.AuthenticationProvider#authenticate(org.springframework.security.core.Authentication)
	 */
	public Authentication authenticate ( Authentication authentication ) throws AuthenticationException {
		log.info("Authenticating the user [" + authentication.getPrincipal() + "]");
		
		Object userName = authentication.getPrincipal();
		Object credentials = authentication.getCredentials();
		
		if(userName == null || userName.toString().length() == 0){
			throw new UsernameNotFoundException("You must specify an user id");
		}
		if(credentials == null || credentials.toString().length() == 0){
			throw new BadCredentialsException("You must specify a password");
		}
		
		try {
			//TODO uncomment this!
			/*userService.doAuthentication(userName.toString(), credentials.toString());*/
			
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("ROLE_ACCESS"));
			
			UserOld currentUser = new UserOld(userName.toString());
			
			UsernamePasswordAuthenticationToken auth = 
					new UsernamePasswordAuthenticationToken(currentUser, 
															authentication.getCredentials(), 
															authorities);
			
			/*SecurityContextHolder.getContext().setAuthentication(auth);*/
			
			return auth;
		} catch (Exception e) {
			throw new AuthenticationServiceException(e.getMessage());
		} 
	}
}