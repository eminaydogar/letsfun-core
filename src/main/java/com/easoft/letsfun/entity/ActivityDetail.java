package com.easoft.letsfun.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Data
@Entity

@Table(name = "activity_detail")
public class ActivityDetail extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ticket_content_id")
	private Long ticketContentId;

	@Column(name = "participants_number")
	private Long participantsNumber;

	@Column(name = "cdate")
	private Date cdate;

	@Column(name = "feature_values")
	private String featureValues;

	@Column(name = "address")
	private String address;

	@Column(name = "address_detail")
	private String addressDetail;

}
