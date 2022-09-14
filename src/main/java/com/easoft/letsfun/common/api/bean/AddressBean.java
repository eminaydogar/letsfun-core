package com.easoft.letsfun.common.api.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long cityCode;
	private String cityName;
	private Long districtCode;
	private String districtName;

}
