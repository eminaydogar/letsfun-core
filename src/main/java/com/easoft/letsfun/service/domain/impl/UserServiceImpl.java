package com.easoft.letsfun.service.domain.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easoft.letsfun.common.dto.UserDto;
import com.easoft.letsfun.common.exception.ServiceOperationException;
import com.easoft.letsfun.entity.UserDefinition;
import com.easoft.letsfun.repository.UserRepository;
import com.easoft.letsfun.service.domain.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl extends BaseDomainService implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDefinition getVerifiedUserById(Long id) {

		UserDefinition user = null;

		try {
			user = userRepository.findById(id).orElse(null);

		} catch (Exception e) {

		}

		return user;
	}

	@Override
	public UserDefinition getUserByUsername(String username) {
		UserDefinition user = null;

		try {
			user = userRepository.findByUsername(username);
		} catch (Exception e) {

			System.out.println("repo hata "+ e.getMessage());
		}

		return user;
	}

	@Override
	public UserDefinition getUserById(Long id) {
		UserDefinition user = null;

		try {
			user = userRepository.findById(id).orElse(null);

		} catch (Exception e) {

		}

		return user;
	}

	@Override
	public UserDefinition save(UserDto dto) {

		UserDefinition user = null;

		try {
			boolean isExist = userRepository.existsByUsernameOrEmail(dto.getUsername(), dto.getEmail());
			if (!isExist) {
				user = userRepository.save(dto.copyToEntity(new UserDefinition()));
			} else {
				throw new ServiceOperationException("Username or Email allready exist");
			}

		} catch (Exception e) {

		}
		return user;
	}

	@Override
	public UserDefinition update(UserDto dto) {

		UserDefinition user = null;

		try {
			user = userRepository.findById(dto.getId()).orElse(null);
			if (user != null) {
				user = userRepository.saveAndFlush(dto.copyToEntity(user));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return user;
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
