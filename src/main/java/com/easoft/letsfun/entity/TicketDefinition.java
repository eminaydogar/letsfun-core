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
@Data
@EqualsAndHashCode(callSuper=false)
@Entity

@Table(name = "ticket_definition")
public class TicketDefinition extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private UserDefinition user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "activity_id", referencedColumnName = "id")
	private ActivityDefinition activity;

	@Column(name = "content")
	private String content;

	@Column(name = "cdate")
	private Date cdate;

	@Column(name = "status")
	private String status;

}
