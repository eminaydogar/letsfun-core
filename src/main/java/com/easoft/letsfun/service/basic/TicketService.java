package com.easoft.letsfun.service.basic;

import java.util.List;

import com.easoft.letsfun.common.dto.TicketDto;

public interface TicketService {

	List<TicketDto> getTicketListByUserId(Long id);

	List<TicketDto> getTicketListByActivityId(Long id);

	List<TicketDto> getTicketList();

	TicketDto getTicketWithActivityById(Long id);

	TicketDto getTicketWithWithUserById(Long id);

	TicketDto getTicketById(Long id);

	public TicketDto save(TicketDto dto);

	public TicketDto update(TicketDto dto);

}
