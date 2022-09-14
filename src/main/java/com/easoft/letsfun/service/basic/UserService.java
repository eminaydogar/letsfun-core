package com.easoft.letsfun.service.basic;

import java.util.List;

import com.easoft.letsfun.common.dto.UserDto;

public interface UserService {

	public UserDto getUserWithRolesByUsername(String username);
	
	public UserDto getSimpleUserByUsername(String username);
	
	public UserDto getVerifiedSimpleUserById(Long id);

	public UserDto getSimpleUserById(Long id);
	
	public UserDto getFullUserById(Long id);
	
	public boolean existUserControlByUsernameOrEmail(String username,String email);

	public List<UserDto> getFullUserList();
	
	public List<UserDto> getSimpleUserList();

	public UserDto save(UserDto dto);

	public UserDto update(UserDto dto);


}
