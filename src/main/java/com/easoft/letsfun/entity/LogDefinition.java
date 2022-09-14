package com.easoft.letsfun.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "log_definition")
public class LogDefinition extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "method_name")
	private String methodName;

	@Column(name = "process_type")
	private Long processType;

	@Column(name = "result_code")
	private String resultCode;

	@Column(name = "result_explanation")
	private String resultExplanation;

	@Column(name = "client_ip")
	private String clientIp;

	@Column(name = "cdate")
	private Date cdate;
}
