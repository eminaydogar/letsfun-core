package com.easoft.letsfun.common.aspect;

import java.lang.reflect.Field;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.easoft.letsfun.common.CoreQueryService;
import com.easoft.letsfun.common.exception.AuthorizationException;
import com.easoft.letsfun.entity.UserDefinition;
import com.easoft.letsfun.security.RoleType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class CheckAuthAspect {

	public CheckAuthAspect() {

	}

	// @Around("@annotation(CheckAuth)")
	// @Before("execution(* com.easoft.letsfun.controller..*(..))")
	@Before("@annotation(CheckAuth)")
	public void authControl(JoinPoint joinPoint) throws AuthorizationException {

		log.debug("auth control for service");

		Object requestValue = joinPoint.getArgs()[0];
		Authentication authUser = SecurityContextHolder.getContext().getAuthentication();

		Field[] requestFields = requestValue.getClass().getDeclaredFields();
		Long userId = null;

		if (requestFields != null) {

			boolean isAdmin = false;
			for (GrantedAuthority role : authUser.getAuthorities()) {
				if (role.getAuthority().equalsIgnoreCase("ROLE_" + RoleType.ADMIN.getName())) {
					isAdmin = true;
					break;
				}
			}

			if (!isAdmin) {

				for (Field requestField : requestFields) {
					try {
						requestField.setAccessible(true);
						if (requestField.getName().equals("userId") || requestField.getName().equals("userid")) {
							userId = Long.valueOf(requestField.get(requestValue).toString());
							break;
						}
					} catch (Exception e) {
						requestField.setAccessible(false);
						throw new AuthorizationException("Cannot forward request. Auth security dedected , ");
					} finally {
						requestField.setAccessible(false);
					}
				}

				if (userId != null && authUser != null && authUser.getName() != null) {

					UserDefinition user = CoreQueryService.findByNativeQuery(
							"select * from user_definition where id=? and username=? ", UserDefinition.class, userId,
							authUser.getName());

					if (user == null) {
						CoreQueryService.executeQuery("update user_definition set is_black_list='Y' where username=?",
								authUser.getName());
						throw new AuthorizationException("Cannot forward request. Auth security dedected");

					}
				} else {

					throw new AuthorizationException("Cannot forward request. Auth security dedected");

				}

			}

		}

	}

}
