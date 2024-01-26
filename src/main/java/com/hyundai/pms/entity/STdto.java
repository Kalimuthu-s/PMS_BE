package com.hyundai.pms.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class STdto {
	

	private int skillTransactionId;
	
	private int employeeId;
	
	private List<Integer> skillId;
	
//	private String proficiencyLevel;

}
