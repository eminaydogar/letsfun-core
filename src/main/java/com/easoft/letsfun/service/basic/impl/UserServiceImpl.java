package com.easoft.letsfun.service.basic.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easoft.letsfun.common.dto.RoleDto;
import com.easoft.letsfun.common.dto.UserDto;
import com.easoft.letsfun.common.exception.ServiceOperationException;
import com.easoft.letsfun.entity.RoleDefinition;
import com.easoft.letsfun.entity.UserDefinition;
import com.easoft.letsfun.repository.UserRepository;
import com.easoft.letsfun.service.basic.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class UserServiceImpl extends BaseDomainService implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto getVerifiedSimpleUserById(Long id) {

		UserDefinition user = null;
		UserDto userDto = null;
		try {
			user = userRepository.findById(id).orElse(null);
			if (user != null && "Y".equalsIgnoreCase(user.getVertify())) {
				userDto = new UserDto(user);
			}
		} catch (Exception e) {

		}

		return userDto;
	}

	@Override
	public UserDto getUserByUsername(String username, Class<?>... entities) {
		UserDefinition user = null;
		UserDto userDto = null;
		try {
			user = userRepository.findByUsername(username);
			if (user != null) {
				userDto = new UserDto(user);
		        //userDto.setEntityRoles(user.getRoles());
				
				lazyInvoke(user, userDto, entities);
			}
		} catch (Exception e) {

		}

		return userDto;
	}

	@Override
	public UserDto getSimpleUserById(Long id) {
		UserDefinition user = null;
		UserDto userDto = null;
		try {
			user = userRepository.findById(id).orElse(null);
			if (user != null) {
				userDto = new UserDto(user);
			}
		} catch (Exception e) {

		}

		return userDto;
	}

	@Override
	public UserDto getUserById(Long id, Class<?>... entities) {
		UserDefinition user = null;
		UserDto userDto = null;
		try {
			user = userRepository.findById(id).orElse(null);
			if (user != null) {
				userDto = new UserDto(user);
				lazyInvoke(user, userDto, entities);
			}
		} catch (Exception e) {

		}

		return userDto;
	}

	@Override
	public List<UserDto> getFullUserList() {
		List<UserDefinition> userList = null;
		List<UserDto> userDtoList = null;
		try {
			userList = userRepository.findAll();
			userDtoList = new ArrayList<>();
			if (userList != null && !userList.isEmpty()) {
				for (UserDefinition user : userList) {
					userDtoList.add(new UserDto(user));
				}
			}

		} catch (Exception e) {

		}

		return userDtoList;
	}

	@Override
	public List<UserDto> getSimpleUserList() {
		List<UserDefinition> userList = null;
		List<UserDto> userDtoList = null;
		try {
			userList = userRepository.findAll();
			userDtoList = new ArrayList<>();
			if (userList != null && !userList.isEmpty()) {
				for (UserDefinition user : userList) {
					userDtoList.add(new UserDto(user));
				}
			}

		} catch (Exception e) {

		}

		return userDtoList;
	}

	@Override
//	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public UserDto save(UserDto dto) {

		UserDto result = null;
		UserDefinition user = dto.copyToEntity(new UserDefinition());

		try {
			boolean isExist = userRepository.existsByUsernameOrEmail(dto.getUsername(), dto.getEmail());
			if (!isExist) {
				user.setCdate(new Date());
				user = userRepository.save(user);
				result = new UserDto(user);
			} else {
				throw new ServiceOperationException("Username or Email allready exist");
			}

		} catch (Exception e) {

		}
		return result;
	}

	@Override
//	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public UserDto update(UserDto dto) {

		UserDto result = null;
		UserDefinition user = null;

		try {
			user = userRepository.findById(dto.getId()).orElse(null);
			if (user != null) {
				user = userRepository.saveAndFlush(dto.copyToEntity(user));
				result = new UserDto(user);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	@Override
	public boolean existUserControlByUsernameOrEmail(String username, String email) {

		boolean isExist = true;

		try {
			isExist = userRepository.existsByUsernameOrEmail(username, email);
		} catch (Exception e) {
			log.error(email);
		}
		return isExist;
	}

}
