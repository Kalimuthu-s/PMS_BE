package com.hyundai.pms.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Consolidate {
	
	private int empid;

	private int projectid;

	private Date startDate;

	private Date endDate;

}
