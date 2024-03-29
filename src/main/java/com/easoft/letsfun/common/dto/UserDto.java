package com.easoft.letsfun.common.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.easoft.letsfun.entity.BaseEntity;
import com.easoft.letsfun.entity.RoleDefinition;
import com.easoft.letsfun.entity.UserDefinition;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String username;
	private String password;
	private String email;
	private String address;
	private Long phoneNumber;
	private String vertify;
	private Date cdate;
	private Date lastLoginDate;
	private String isBlackList;
	private byte[] image;
	private String status;
	private Set<RoleDto> roles = new HashSet<>();

	public UserDto() {

	}

	public UserDto(UserDefinition user) {
		setter(user);
	}

	private void setter(UserDefinition user) {
		if (user != null) {
			this.id = user.getId();
			this.username = user.getUsername();
			this.password = user.getPassword();
			this.email = user.getEmail();
			this.isBlackList = user.getIsBlackList();
			this.phoneNumber = user.getPhoneNumber();
			this.address = user.getAddress();
			this.vertify = user.getVertify();
			this.status = user.getStatus();
		}
	}

	public void setEntityRoles(Set<RoleDefinition> roles) {
		if (roles != null) {
			for (RoleDefinition role : roles) {
				RoleDto model = new RoleDto(role);
				this.roles.add(model);
			}
		}
	}

	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}

	private Set<RoleDefinition> userRoleEntityConverter(Set<RoleDto> roles) {
		Set<RoleDefinition> roleSet = new HashSet<RoleDefinition>();
		if (roles != null) {
			for (RoleDto roleDto : roles) {
				RoleDefinition model = roleDto.copyToEntity(new RoleDefinition());
				roleSet.add(model);
			}
		}
		return roleSet;
	}

	@Override
	public UserDefinition copyToEntity(BaseEntity entity) {
		UserDefinition model = (UserDefinition) entity;
		model.setId(id);
		model.setAddress(address);
		model.setUsername(username);
		model.setPassword(password);
		model.setEmail(email);
		model.setPassword(password);
		model.setVertify(vertify);
		model.setCdate(cdate);
		model.setImage(image);
		model.setLastLoginDate(lastLoginDate);
		model.setIsBlackList(isBlackList);
		model.setStatus(status);
		if (roles != null) {
			model.setRoles(userRoleEntityConverter(roles));
		}

		return model;
	}

	@Override
	public void setLazyClass(BaseEntity baseEntity) {
		if (baseEntity instanceof RoleDefinition) {
			RoleDto roleDto = new RoleDto((RoleDefinition) baseEntity);
			roles.add(roleDto);
		}

	}

}
