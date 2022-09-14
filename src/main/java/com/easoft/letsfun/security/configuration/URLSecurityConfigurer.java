package com.easoft.letsfun.security.configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.easoft.letsfun.common.CoreQueryService;
import com.easoft.letsfun.entity.RoleDefinition;
import com.easoft.letsfun.entity.URLSecurityDefinition;
import com.easoft.letsfun.security.RoleType;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class URLSecurityConfigurer {

	@Getter
	private Map<String, RoleType[]> urlSecurityMap = new HashMap<String, RoleType[]>();

	@PostConstruct
	private void init() {
		fillSecurityMap();
	}

	private void fillSecurityMap() {

		try {
			log.info("fillSecurityMap start");

			List<URLSecurityDefinition> urlSecurityDefinitionList = CoreQueryService.findListByNativeQuery(
					"Select * from url_security_definition where status = ?",URLSecurityDefinition.class,'Y');

			if (urlSecurityDefinitionList != null) {
				for (URLSecurityDefinition urlSecurityDefinition : urlSecurityDefinitionList) {
					if (urlSecurityDefinition.getRoles() != null) {
						RoleType[] roleTypes = new RoleType[urlSecurityDefinition.getRoles().size()];
						int pointer = 0;
						for (RoleDefinition role : urlSecurityDefinition.getRoles()) {
							if (role.getId().equals(RoleType.ADMIN.getId())) {
								roleTypes[pointer] = RoleType.ADMIN;
							} else if (role.getId().equals(RoleType.USER.getId())) {
								roleTypes[pointer] = RoleType.USER;
							}
							pointer++;

						}
						urlSecurityMap.put(urlSecurityDefinition.getPath(), roleTypes);
					}

				}
			}

		} catch (Exception e) {
			log.error("URLSecurityConfigurer ERROR !! ", e);
		}

	}

}
