package com.easoft.letsfun.common.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketRequest {
	
	private Long userId;
	private String ticketContent;

}
