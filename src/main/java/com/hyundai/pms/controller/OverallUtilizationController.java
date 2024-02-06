package com.hyundai.pms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.pms.entity.Consolidate;
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.service.OverallUtilizationService;

@RestController
@CrossOrigin
@RequestMapping(name = "/employeeOverallUtilization")
public class OverallUtilizationController {
	
	@Autowired
	private OverallUtilizationService overallUtilizationRepo;
	
	@PostMapping("/employeeOverallFromTo")
	public Response EmployeeOverallData(@RequestBody Consolidate  consolidate) {
		return overallUtilizationRepo.getOverallConsolidatedUsingDate(consolidate);
	}
	
	
	@GetMapping("/getAllConsolidatedDatas")
	public Response getAllConsolidatedData() {
		return overallUtilizationRepo.getOverAllConsolidatedData();
	}
	
	@PostMapping("/getAllDatasByProject")
	public Response getAllDatasByProject(@RequestBody Consolidate consolidate) {
		System.err.println(">>>>>>>>>>>"+consolidate.getProjectid()+consolidate.getStartDate()+consolidate.getEndDate());
			return overallUtilizationRepo.getByProjectsData(consolidate);
	}

}