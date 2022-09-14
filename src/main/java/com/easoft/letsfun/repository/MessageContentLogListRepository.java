package com.easoft.letsfun.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.easoft.letsfun.entity.MessageContentLogList;

@Repository
public interface MessageContentLogListRepository extends JpaRepository<MessageContentLogList, Long> {

	//JPLQUERY
	@Query(value = "select m from MessageContentLogList m where m.messageType=?1 and m.relatedUser.id=?2 and m.status=?3")
	List<MessageContentLogList> findByMessageTypeAndRelatedUserAndStatus(Long messageType, Long relatedUserId,
			String status);

}
