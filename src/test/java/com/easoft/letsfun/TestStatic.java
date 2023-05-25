package com.easoft.letsfun;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;

import com.easoft.letsfun.common.aspect.LazyInvoke;
import com.easoft.letsfun.common.dto.ActivityJoinRequestDto;
import com.easoft.letsfun.entity.ActivityDefinition;
import com.easoft.letsfun.entity.ActivityJoinRequest;
import com.easoft.letsfun.entity.BaseEntity;
import com.easoft.letsfun.entity.UserDefinition;

public class TestStatic {

	public static void main(String[] args)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		ActivityJoinRequestDto result = null;

		ActivityJoinRequest entity = null;

		try {
			Class[] callClasses = {UserDefinition.class};
			entity = new ActivityJoinRequest();
			ActivityDefinition activity = new ActivityDefinition();
			activity.setId(2L);
			UserDefinition userDef = new UserDefinition();
			userDef.setId(3L);
			entity.setId(1L);

			 entity.setActivity(activity);
			 entity.setUser(userDef);

				try {

	
				} catch (Exception e) {

				}

		} catch (Exception e) {
			System.out.println("HATA " + e.getMessage());
		}

	}

}
