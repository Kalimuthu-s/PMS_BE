package com.hyundai.pms.entity;

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
public class SkillGetDTO {
	
	private int skillTransactionId;
	
	private int employeeId;
	
	private int skillId;
	
	private String proficiencyLevel;
	
	private String skillName;
	
	private String skillCategory;

}
