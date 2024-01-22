package com.hyundai.pms.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectReassignDTO {
	
	private String managerId;
	private String assignManagerId;
	private List<Integer> projectList;

}
