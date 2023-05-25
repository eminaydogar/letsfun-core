package com.easoft.letsfun.common.dto;

import com.easoft.letsfun.entity.BaseEntity;
import com.easoft.letsfun.entity.RoleDefinition;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6704737554003440426L;
	private Long id;
	private String name;
	private String roleCode;

	public RoleDto(RoleDefinition role) {
		this.id = role.getId();
		this.name = role.getName();
		this.roleCode = role.getRoleCode();
	}

	public RoleDto() {
	}

	@Override
	public RoleDefinition copyToEntity(BaseEntity entity) {
		RoleDefinition model = (RoleDefinition) entity;
		model.setId(id);
		model.setName(name);
		return model;
	}

	@Override
	public void setLazyClass(BaseEntity entity) {
		// TODO Auto-generated method stub
		
	}

}
