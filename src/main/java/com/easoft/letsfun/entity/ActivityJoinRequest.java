package com.easoft.letsfun.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Data
@Entity

@Table(name = "activity_join_request")
public class ActivityJoinRequest extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "activity_id", referencedColumnName = "id")
	private ActivityDefinition activity;

	@Column(name = "request_status")
	private String requestStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "requesting_user_id", referencedColumnName = "id")
	private UserDefinition user;

	@Column(name = "reject_reason")
	private String rejectReason;

	@Column(name = "cdate")
	private Date cdate;

}
