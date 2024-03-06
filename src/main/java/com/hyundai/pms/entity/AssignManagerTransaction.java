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
@Table(name = "assign_manager_transaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssignManagerTransaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "proj_man_trans_id")
	private int projectManagerTransactionId;
	@Column(name = "project_id")
	private String projectName;
	@Column(name = "manager_id")
	private String managerName;
	@Column(name = "start_date")
	private String startDate;
	@Column(name = "end_date")
	private String endDate;
	@Column(name = "status")
	private String status;

}
