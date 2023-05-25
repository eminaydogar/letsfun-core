package com.easoft.letsfun.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.easoft.letsfun.common.aspect.LazyInvoke;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Data
@Entity

@Table(name = "activity_definition")
public class ActivityDefinition extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "capacity")
	private Long capacity;
	
	@Column(name = "type_id")
	private Long typeId;
	
	@Column(name = "advert_image",columnDefinition = "BLOB")
	private byte[] advertImage;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "cdate")
	private Date cdate;
	
	@Column(name = "edate")
	private Date edate;
	
	@LazyInvoke
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuser_id", referencedColumnName = "id")
    private UserDefinition createdUser;
    
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "activity_detail_id", referencedColumnName = "id")
    private ActivityDetail details;

}
