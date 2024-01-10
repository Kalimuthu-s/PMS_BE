package com.hyundai.pms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_master")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;
	@Column(name = "name")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "role_id")
	private String roleId;
	@Column(name = "status")
	private boolean status;

}
