package com.easoft.letsfun.repository;

import java.util.List;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface CoreRepository<T> {

	public T findByNativeQuery(String queryString, Class<T> clazz, Object... params);

	public List<T> findListByNativeQuery(String queryString, Class<T> clazz, Object... params);

}
