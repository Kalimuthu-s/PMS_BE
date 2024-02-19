package com.hyundai.pms.entity;

import java.util.Date;
import java.util.List;

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
public class EmployeeTransactionDTO {
	
	private int projectEmpId;
	
	private int projectId;
	
	private List<Long> employeeId;
	
	private Date assignedStartDate;
	
	private Date assignedEndDate;


}
