package com.easoft.letsfun.common.api.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityDetailBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long ticketContentId;

	private Long participantsNumber;

	private Date cdate;

	private List<ObjectValueBean> features;

	private AddressBean address;

	private String addressDetail;

}
