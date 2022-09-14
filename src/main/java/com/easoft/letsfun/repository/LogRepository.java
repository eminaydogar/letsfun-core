package com.easoft.letsfun.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easoft.letsfun.entity.LogDefinition;

public interface LogRepository extends JpaRepository<LogDefinition,Long> {

}
