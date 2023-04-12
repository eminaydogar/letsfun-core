# letsfun-core
lestfun app with spring
LOOK Mapper

public final class Mapper {

	public HashMap<String, Object> copyToMap(HashMap<String, Object> source, HashMap<String, Object> target,
			String... props) {
		if (source != null && target != null) {
			if (props != null && props.length > 0) {
				for (String prop : props) {
					target.put(prop, source.get(prop));
				}
			}
		} else {
			source.keySet().stream().forEach(p -> target.put(p, source.get(p)));
		}
		return target;
	}

	public <T> T getValue(String key, HashMap<?, ?> source) {
		T value = null;
		try {
			value = (T) source.get(key);
		} catch (Exception e) {
			// error
		}
		return value;
	}

	public void copyClassToMap(HashMap<String, Object> map, Object clazz, String... props) {

		Field[] fields = clazz.getClass().getDeclaredFields();

		for (Field field : fields) {

			field.setAccessible(true);

			Object fieldObject = null;

			if (!Modifier.isStatic(field.getModifiers())) {

				try {
					fieldObject = field.get(clazz);
				} catch (Exception e) {
					System.out.println("warn : " + e.getMessage());
					field.setAccessible(false);
					continue;
				}

				if(fieldObject!=null) {
					map.put(field.getName(), fieldObject);
				}
				

				field.setAccessible(false);

			}

			if (props != null && props.length > 0) {

				for (String prop : props) {

					if (!isKeyContains(prop, map)) {
						map.remove(prop);
					}
				}
			}

		}
	}

	public void copyMapToClass(HashMap<String, ?> map, Object clazz) {

		Field[] fields = clazz.getClass().getDeclaredFields();

		for (Field field : fields) {

			field.setAccessible(true);

			if (!Modifier.isStatic(field.getModifiers())) {

				try {
					field.set(clazz, map.get(field.getName()));
				} catch (Exception e) {
					System.out.println("warn : " + e.getMessage());
					field.setAccessible(false);
					continue;
				}
				field.setAccessible(false);

			}

		}

	}

	public boolean isEmpty(HashMap<?, ?> map) {

		return map == null || map.isEmpty();
	}

	public boolean isKeyContains(String key, HashMap<String, ?> map) {
		return map.keySet().contains(key);
	}
  
}

###################### Lazy Invoker #################################


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
