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
@Table(name = "team_master")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "team_id")
	private int teamId;
	@Column(name = "team_name")
	private String teamName;
	@Column(name = "dept_id")
	private int deptId;
	@Column(name = "team_grade")
	private int teamGrade;

}
