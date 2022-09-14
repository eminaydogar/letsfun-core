package com.easoft.letsfun.common;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

@Service
public class CoreQueryService {

	@PersistenceContext
	private EntityManager entityManager;

	private static EntityManager manager;

	@PostConstruct
	private void init() {
		manager = entityManager;
	}

	public static <T> T findByNativeQuery(String queryString, Class<T> clazz, Object... params) {

		T result = null;
		try {
			Query query = manager.createNativeQuery(queryString, clazz);
			if (params != null) {
				for (int i = 1; i <= params.length; i++) {
					query.setParameter(i, params[i - 1]);
				}
			}
			List<?> queryResult = query.getResultList();
			if (queryResult != null && queryResult.size() == 1) {
				result = (T) queryResult.get(0);
			}
		} catch (Exception e) {
			// log.error("(get)" + e);
		}
		return result;
	}

	public static <T> List<T> findListByNativeQuery(String queryString, Class<T> clazz, Object... params) {
		List<T> result = null;
		try {
			Query query = manager.createNativeQuery(queryString, clazz);
			if (params != null) {
				for (int i = 1; i <= params.length; i++) {
					query.setParameter(i, params[i - 1]);
				}
			}
			result = query.getResultList();

		} catch (Exception e) {
			// log.error("(get)" + e);
		}
		return result;
	}

	public static int executeQuery(String queryString, Object... params) {
		int result = 0;
		try {

			Query query = manager.createNativeQuery(queryString);

			if (params != null) {
				for (int i = 1; i <= params.length; i++) {
					query.setParameter(i, params[i - 1]);
				}
			}

			result = query.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

}
