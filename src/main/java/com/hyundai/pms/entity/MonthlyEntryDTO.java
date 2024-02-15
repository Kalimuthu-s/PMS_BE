package com.hyundai.pms.entity;

import java.util.Date;

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
public class MonthlyEntryDTO {
	
	private String employeeId;
	
	private String projectId;
	
	private Date startDate;
	
	private Date endDate;
	
	private String year;
	
}