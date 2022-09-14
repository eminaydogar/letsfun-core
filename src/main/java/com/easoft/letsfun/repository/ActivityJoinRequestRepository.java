package com.easoft.letsfun.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.easoft.letsfun.entity.ActivityJoinRequest;

@Repository
public interface ActivityJoinRequestRepository extends JpaRepository<ActivityJoinRequest, Long> {

	@Query(value = "select a from ActivityJoinRequest a where a.user.id = ?1 ")
	List<ActivityJoinRequest> findAllByUserId(Long id);

	@Query(value = "select a from ActivityJoinRequest a where a.activity.id = ?1 ")
	List<ActivityJoinRequest> findAllByActivityId(Long id);
	
	List<ActivityJoinRequest> findAllByRequestStatus(String status);

}
