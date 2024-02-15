package com.hyundai.pms.entity;

import java.util.List;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeTransactionDTO {
	
	private int projectEmpId;
	
	private int projectId;
	
	private List<Long> employeeId;
	
	private String assignedStartDate;
	
	private String assignedEndDate;


}
