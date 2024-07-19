package com.easoft.letsfun.security.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.easoft.letsfun.common.Constants.RECORD_STATUS;
import com.easoft.letsfun.common.Constants.USER_STATUS;
import com.easoft.letsfun.entity.RoleDefinition;
import com.easoft.letsfun.entity.UserDefinition;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Setter;

public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 2396654715019746670L;
	private static final String AUTH_ROLE = "ROLE_";

	@Setter
	private UserDefinition user;

	public CustomUserDetails(UserDefinition user) {
		this.user = user;
	}

	public CustomUserDetails() {

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		for (RoleDefinition role : this.user.getRoles())
			authorities.add(new SimpleGrantedAuthority(AUTH_ROLE + role.getName()));
		return authorities;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return RECORD_STATUS.INACTIVE.equals(user.getIsBlackList());
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return USER_STATUS.ACTIVE.equals(user.getStatus());
	}

}
