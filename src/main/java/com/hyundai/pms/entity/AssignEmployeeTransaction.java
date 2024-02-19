package com.hyundai.pms.entity;

import java.util.Date;

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
@Table(name = "assign_employee_transaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssignEmployeeTransaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "proj_emp_id")
	private int projectEmpId;
	
	@Column(name = "project_id")
	private int projectId;
	
	@Column(name = "employee_id")
	private Long employeeId;
	
	@Column(name = "assigned_start_date")
	private Date assignedStartDate;
	
	@Column(name = "assigned_end_date")
	private Date assignedEndDate;

}
