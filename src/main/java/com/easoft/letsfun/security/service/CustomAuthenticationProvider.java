package com.easoft.letsfun.security.service;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.easoft.letsfun.security.PasswordCrypter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private final UserSecurityService securityService;

	public CustomAuthenticationProvider(UserSecurityService securityService) {
		this.securityService = securityService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		UserDetails user = securityService.loadUserByUsername(username);
			
		if (user==null || !username.equalsIgnoreCase(user.getUsername())
				|| !PasswordCrypter.instance().matches(password, user.getPassword())) {
			log.info("user not found");
			throw new AuthenticationServiceException("User not found ! Check username or password");
		}else if(!user.isAccountNonLocked()) {
			log.info("user locked");
			throw new AuthenticationServiceException("User lock !");
		}else if(!user.isEnabled()) {
			log.info("user disable");
			throw new AuthenticationServiceException("User account disable : status 'N' !");
		}

		return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}