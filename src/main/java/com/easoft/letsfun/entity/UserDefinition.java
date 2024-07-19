package com.easoft.letsfun.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "user_definition")
public class UserDefinition extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "username",nullable = false,length = 128,updatable = false)
	private String username;

	@Column(name = "password",nullable = false,length = 256)
	private String password;

	@Column(name = "email",nullable = false,length = 128)
	private String email;
	
	@Column(name = "phone_number",nullable = false,length = 128)
	private Long phoneNumber;
	
	@Column(name = "address",nullable = false,length = 1024)
	private String address;
	
	@Column(name="is_black_list")
	private String isBlackList;
	
	@Column(name="cdate")
	private Date cdate;
	
	@Column(name="last_login_date")
	private Date lastLoginDate;
	
	@Column(name="vertify")
	private String vertify;

	@Lob
	@Column(name="image")
	private byte[] image;
	
	@Column(name="status")
	private String status;


	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role_list", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private Set<RoleDefinition> roles;
	



}
