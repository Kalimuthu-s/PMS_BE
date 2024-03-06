package com.hyundai.pms.entity;

import java.util.Date;
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
public class AssignEmployeeSearchDTO {

//	private int skillTransactionId;
//	
//	private int employeeId;
//	
//	private int skillId;
//	
//	private String proficiencyLevel;
	
	private String skill;
	private String proficiency;
	private String assignedStartDate;
	private String assignedEndDate;

}
