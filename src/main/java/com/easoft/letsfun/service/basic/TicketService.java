package com.easoft.letsfun.service.basic;

import java.util.List;

import com.easoft.letsfun.common.dto.TicketDto;

public interface TicketService {

	List<TicketDto> getTicketListByUserId(Long id, Class<?>... entites);

	List<TicketDto> getTicketListByActivityId(Long id, Class<?>... entites);

	List<TicketDto> getTicketList();

	TicketDto getTicketById(Long id, Class<?>... entites);

	public TicketDto save(TicketDto dto);

	public TicketDto update(TicketDto dto);

}
