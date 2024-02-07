package com.hyundai.pms.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pendingrequest")
public class PendingRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pending_id")
	public int pendingRequestId;
	
	@Column(name="emp_month_id")
	public int empMonthId;
	
	@Column(name="manager_id")
	public int managerId;
	
	@Column(name="date")
	public LocalDate date;
	
	@Column(name="status")
	public String status;

	public int getPendingRequestId() {
		return pendingRequestId;
	}

	public void setPendingRequestId(int pendingRequestId) {
		this.pendingRequestId = pendingRequestId;
	}

	public int getEmpMonthId() {
		return empMonthId;
	}

	public void setEmpMonthId(int empMonthId) {
		this.empMonthId = empMonthId;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	
	
	
	

}
