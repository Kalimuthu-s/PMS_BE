package com.hyundai.pms.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "monthly_entries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MonthlyEntries {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="month_id")
	private int monthlyId;
	@Column(name ="emp_id")
	private int emp_id;
	@Column(name="project_id")
	private int projectId;
	@Column(name="january")
	private Double january;
	@Column(name ="february")
	private Double february;
	@Column(name ="march")
	private Double march;
	@Column(name ="april")
	private Double april;
	@Column(name ="may")
	private Double may;
	@Column(name="june")
	private Double june;
	@Column(name="july")
	private Double july;
	@Column(name ="august")
	private Double august;
	@Column(name="september")
	private Double september;
	@Column(name ="october")
	private Double october;
	@Column(name="november")
	private Double november;
	@Column(name ="december")
	private Double december;
	@Column(name ="year")
	private String year;

}
