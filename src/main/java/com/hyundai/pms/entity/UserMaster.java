package com.hyundai.pms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.hyundai.pms.config.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user_master")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserMaster {
	
   	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "custom_seq")
    @Column(name="user_id")
   	@GenericGenerator(
   	        name = "custom_seq",
   	        strategy = "com.hyundai.pms.config.SequenceGenerator", 
   	        parameters = {
   	            @Parameter(name = SequenceGenerator.INCREMENT_PARAM, value = "50"),
   	            @Parameter(name = SequenceGenerator.VALUE_PREFIX_PARAMETER, value = "USER"),
   	            @Parameter(name = SequenceGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	private String userId;
	@Column(name = "name")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "role_id")
	private String roleId;
	@Column(name = "email")
	private String email;
	@Column(name = "status")
	private boolean status;

}
