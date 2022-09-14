package com.easoft.letsfun.security;

import java.util.HashSet;
import java.util.Set;

import com.easoft.letsfun.common.dto.RoleDto;

public enum RoleType {

	/*
	 * USER(2L, "USER", Arrays.asList(RolePermissionType.READ_SELF,
	 * RolePermissionType.WRITE_SELF, RolePermissionType.UPDDATE_SELF)),
	 * DIRECTOR(3L, "DIRECTOR", Arrays.asList(RolePermissionType.READ_ALL,
	 * RolePermissionType.WRITE_SELF, RolePermissionType.UPDDATE_ALL)), ADMIN(1L,
	 * "ADMIN", Arrays.asList(RolePermissionType.READ_ALL,
	 * RolePermissionType.WRITE_ALL, RolePermissionType.UPDDATE_ALL,
	 * RolePermissionType.DELETE));
	 */

	ADMIN(1L, "ADMIN"), USER(2L, "USER");

	private final Long id;
	private final String name;
	// private final List<RolePermissionType> permissions;

	RoleType(Long id, String type) {

		this.id = id;
		this.name = type;
		// this.permissions = permissions;
	}

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}

	public Set<RoleDto> getUserRole() {
		Set<RoleDto> roles = new HashSet<RoleDto>();
		RoleDto role = new RoleDto();
		role.setId(USER.getId());
		role.setRoleCode(USER.getName());
		role.setName(USER.getName());
		roles.add(role);
		return roles;
	}

//	public List<RolePermissionType> getPermissions() {
//		return permissions;
//	}

}
