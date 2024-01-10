package com.hyundai.pms.entity;

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
@Table(name = "project_master")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "project_id")
	private int projectId;
	@Column(name = "customer_id")
	private String customerName;
	@Column(name = "project_name")
	private String projectName;
	@Column(name = "start_date")
	private String startDate;
	@Column(name = "end_date")
	private String endDate;
	@Column(name = "status")
	private String status;
	
	

}
