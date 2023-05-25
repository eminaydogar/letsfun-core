package com.easoft.letsfun.security.configuration;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.easoft.letsfun.security.RoleType;
import com.easoft.letsfun.security.service.CustomAuthenticationProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	private final CustomAuthenticationProvider authProvider;
	private final URLSecurityConfigurer urlSecurityConfigurer;

	public SecurityConfig(CustomAuthenticationProvider authProvider, URLSecurityConfigurer urlSecurityConfigurer) {
		this.authProvider = authProvider;
		this.urlSecurityConfigurer = urlSecurityConfigurer;
	}

	
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
	}


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		

		Map<String, RoleType[]> securityMap = urlSecurityConfigurer.getUrlSecurityMap();
		
		return http.csrf().disable().authorizeRequests(v -> {
			
			v.antMatchers("/test/**", "/swagger-ui/**", "/about").permitAll();
			
			for (String path : securityMap.keySet()) {
				
				RoleType[] roles = securityMap.get(path);
				
				if (roles != null && roles.length > 0) {
					
					String[] roleString = new String[roles.length];
					for (int i = 0; i < roleString.length; i++) {
						roleString[i] = roles[i].getName();
						log.info(path + " has role for " + roleString[i]);
					}
					v.antMatchers(path).hasAnyRole(roleString);
					
				} else {
					
					v.antMatchers(path).permitAll();
					log.info(path + " permited all");
					
				}
			}

			v.anyRequest().authenticated();

		}).httpBasic().and().build();

	}
		

}
