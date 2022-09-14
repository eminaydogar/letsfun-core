package com.easoft.letsfun.entity.resultset;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ObjectValue {

	@Id
	private Long id;

	@Column(name = "object_type")
	private String objectType;

	@Column(name = "object_code")
	private String objectCode;

	@Column(name = "object_name")
	private String objectName;

}
