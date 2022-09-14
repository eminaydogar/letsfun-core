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
@EqualsAndHashCode(callSuper = false)
@Data
@Entity

@Table(name = "message_content_log_list")
public class MessageContentLogList extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "channel_type")
	private Long channelType;

	@Column(name = "message_type")
	private Long messageType;

	@Column(name = "message_content")
	private String messageContent;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "related_user_id", referencedColumnName = "id")
	private UserDefinition relatedUser;

	@Column(name = "status")
	private String status;

	@Column(name = "cdate")
	private Date cdate;

	@Column(name = "edate")
	private Date edate;

}
