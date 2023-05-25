package com.easoft.letsfun.common.dto;

import java.io.Serializable;

import com.easoft.letsfun.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract Object copyToEntity(BaseEntity entity);
	
	public abstract void setLazyClass(BaseEntity entity);
	

}
