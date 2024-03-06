package com.hyundai.pms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.pms.entity.Consolidate;
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.service.OverallUtilizationService;
import com.hyundai.pms.webModel.PaginationWebModel;

@RestController
@CrossOrigin
@RequestMapping("/employeeOverallUtilization")
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
	
//	@GetMapping("/getAllConsolidated/{year}")
//	public Response getAllConsolidated(@PathVariable String year) {
//		return overallUtilizationRepo.getAllConsolidated(year);
//	}
	
	@PostMapping("/getAllConsolidatedData")
	public Response getAllConsolidatedData(@RequestBody PaginationWebModel paginationWebModel) {
		return overallUtilizationRepo.getAllConsolidatedData(paginationWebModel);
	}
	
//	@GetMapping("/getAllConsolidatedData")
//	public Response getAllConsolidatedData() {
//		return overallUtilizationRepo.getAllConsolidatedData();
//	}
	
}
