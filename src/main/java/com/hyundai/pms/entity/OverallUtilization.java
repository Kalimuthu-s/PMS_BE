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
@Table(name = "emp_overall_utilization")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OverallUtilization {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_util_id")
	private int EmployeeUtilizationId;
	
	@Column(name = "emp_id")
	private String employeeId;
	
	@Column(name = "project_id")
	private String projectId;
	
	@Column(name = "month")
	private Date month;
	
	@Column(name = "contribution")
	private int contribution;

}
