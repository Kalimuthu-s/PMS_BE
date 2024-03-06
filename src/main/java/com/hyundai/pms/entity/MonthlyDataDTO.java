package com.hyundai.pms.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyDataDTO {
	
	private Long empId;
	private String employeeName;
	private Long projectId;
	private String projectName;
	private Double january;
	private Double february;
	private Double march;
	private Double april;
	private Double may;
	private Double june;
	private Double july;
	private Double august;
	private Double september;
	private Double october;
	private Double november;
	private Double december;
	private Long empMonthId;
	private int year;
	
	

}
