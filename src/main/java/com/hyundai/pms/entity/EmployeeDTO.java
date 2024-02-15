package com.hyundai.pms.entity;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDTO {
	
	private Long emp_id;

	private String first_name;

	private String last_name;

	private String date_of_birth;

	private String hire_date;

	private String email;

	private String gender;

	private String phoneNumber;

	private String designation;

	private String department;
	
	private String manager;
	
	private String location;
	
	private String team;
	
	private String roleId;
	
	private String experienceId;
	
	private String experienceLevel;
	
	private List<SkillGetDTO> skillList;

}
