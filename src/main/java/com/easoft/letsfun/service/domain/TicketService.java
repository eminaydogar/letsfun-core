package com.easoft.letsfun.service.domain;

import java.util.List;

import com.easoft.letsfun.common.dto.TicketDto;
import com.easoft.letsfun.entity.TicketDefinition;

public interface TicketService {

	List<TicketDefinition> getTicketListByUserId(Long id);

	List<TicketDefinition> getTicketListByActivityId(Long id);

	List<TicketDefinition> getTicketList();

	TicketDefinition getTicketById(Long id);

	public TicketDefinition save(TicketDto dto);

	public TicketDefinition update(TicketDto dto);

}
