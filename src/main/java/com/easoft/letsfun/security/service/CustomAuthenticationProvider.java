package com.easoft.letsfun.security.service;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.easoft.letsfun.entity.UserDefinition;
import com.easoft.letsfun.security.PasswordCrypter;
import com.easoft.letsfun.service.domain.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private final UserService userService;

	public CustomAuthenticationProvider(UserService userService) {
		this.userService = userService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		UserDefinition userDefinition = userService.getUserByUsername(username);
		
		CustomUserDetails user = new CustomUserDetails(userDefinition);
			
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