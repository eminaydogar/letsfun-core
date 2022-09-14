package com.easoft.letsfun.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.easoft.letsfun.entity.TicketDefinition;

@Repository
public interface TicketRepository extends JpaRepository<TicketDefinition, Long> {
	
	@Query(value = "select t from TicketDefinition where t.user.id = ? ")
	List<TicketDefinition> findAllByUserId(Long id);

	@Query(value = "select t from TicketDefinition where t.activity.id = ? ")
	List<TicketDefinition> findAllByActivityId(Long id);
	

}
