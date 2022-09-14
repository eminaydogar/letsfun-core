package com.easoft.letsfun.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.easoft.letsfun.common.dto.UserDto;
import com.easoft.letsfun.service.basic.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserSecurityService {

	@Autowired
	private UserService userService;

	public UserDetails loadUserByUsername(String username) {

		CustomUserDetails customUserDetails = null;
		try {

			UserDto user = userService.getUserWithRolesByUsername(username);
			if(user!=null) {
				customUserDetails = new CustomUserDetails();
				customUserDetails.setUser(user);
			}

		} catch (Exception e) {
			log.error("UserSecurityService error", e);
		}

		return customUserDetails;
	}

}
