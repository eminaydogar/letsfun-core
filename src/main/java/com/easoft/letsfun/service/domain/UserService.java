package com.easoft.letsfun.service.domain;

import com.easoft.letsfun.common.dto.UserDto;
import com.easoft.letsfun.entity.UserDefinition;

public interface UserService {

	public UserDefinition getUserByUsername(String username);

	public UserDefinition getVerifiedUserById(Long id);

	public UserDefinition getUserById(Long id);

	public boolean existUserControlByUsernameOrEmail(String username, String email);

	public UserDefinition save(UserDto dto);

	public UserDefinition update(UserDto dto);
	


}
