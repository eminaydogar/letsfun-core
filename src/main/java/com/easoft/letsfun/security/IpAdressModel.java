package com.easoft.letsfun.security;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IpAdressModel {
	
	private String ipAddress;
	private Date lastRequestTime;
	private int requestCount;

}
