package com.hyundai.pms.entity;

import java.time.LocalDate;

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
import lombok.ToString;

@Entity
@Table(name="pending_request")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

}
