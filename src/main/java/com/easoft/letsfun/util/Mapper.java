package com.easoft.letsfun.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;

import com.easoft.letsfun.entity.UserDefinition;

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

	public static void main(String[] args) {

		Mapper mapper = new Mapper();

		HashMap<String, Object> map = new HashMap<>();
		HashMap<String, Object> map2 = new HashMap<>();
		map.put("id", 5L);
		map.put("username", "Emin");
		map.put("gg", 5L);
		map.put("ss", "Emin");

		UserDefinition user = new UserDefinition();
		mapper.copyMapToClass(map, user);
		mapper.copyClassToMap(map2, user);
		System.out.println(user);
		map2.keySet().forEach(k->System.out.println(k+ " : " +map2.get(k)));
		Long id = mapper.getValue("id", map2);
		System.out.println(id);
	}
}
