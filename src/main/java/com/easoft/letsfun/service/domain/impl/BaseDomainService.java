package com.easoft.letsfun.service.domain.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

import com.easoft.letsfun.common.aspect.LazyInvoke;
import com.easoft.letsfun.common.dto.BaseDto;
import com.easoft.letsfun.entity.BaseEntity;

public abstract class BaseDomainService {

	public void lazyInvoke(BaseEntity entity, BaseDto dto, Class<?>... callClasses) {

		try {
			if (entity != null && callClasses != null && callClasses.length > 0) {

				Field[] entityFields = entity.getClass().getDeclaredFields();

				for (Class<?> clazz : callClasses) {

					for (Field entityField : entityFields) {

						LazyInvoke lazyAnno = entityField.getDeclaredAnnotation(LazyInvoke.class);

						if (lazyAnno != null) {

							entityField.setAccessible(true);

							if (entityField != null) {
								String entityFieldtypeName = getTypeName(entityField, entity);

								if (clazz.getTypeName().equals(entityFieldtypeName)) {
									String objectName = entityField.getName().substring(0, 1).toUpperCase()
											.concat(entityField.getName().substring(1));

									Object lazyObject = entity.getClass().getMethod("get" + objectName).invoke(entity);

									if (lazyObject instanceof Collection) {
										Collection<?> collObj = (Collection<?>) lazyObject;
										collObj.forEach(obj -> dto.setLazyClass((BaseEntity) obj));
									} else {
										dto.setLazyClass((BaseEntity) lazyObject);
									}

									break;
								}

							}
							entityField.setAccessible(false);
						}
					}

				}
			}
		} catch (Exception e) {
			System.out.println("HATA " + e.getMessage());
		}

	}

	private String getTypeName(Field field, BaseEntity entity) throws Exception {

		Object entityFieldObj = field.get(entity);

		if (entityFieldObj != null) {

			if (entityFieldObj instanceof Collection) {
				Type genericFieldType = field.getGenericType();

				if (genericFieldType instanceof ParameterizedType) {
					ParameterizedType aType = (ParameterizedType) genericFieldType;
					Type[] fieldArgTypes = aType.getActualTypeArguments();
					for (Type fieldArgType : fieldArgTypes) {
						Class<?> fieldArgClass = (Class<?>) fieldArgType;
						return fieldArgClass.getTypeName();
					}
				}
			} else {
				return entityFieldObj.getClass().getTypeName().split("\\$")[0];
			}
		}

		return null;
	}

}
