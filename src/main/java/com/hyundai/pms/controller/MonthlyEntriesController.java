package com.hyundai.pms.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.pms.entity.MonthlyEntries;
import com.hyundai.pms.entity.MonthlyEntryDTO;
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.service.MonthlyEntriesService;
import com.hyundai.pms.webModel.PaginationWebModel;

@RestController
@RequestMapping("/monthlyEntries")
@CrossOrigin
public class MonthlyEntriesController {

	@Autowired
	private MonthlyEntriesService monthlyEntriesService;

	@PostMapping("/saveMonthlyEntry")
	public Response saveMonthlyEntry(@RequestBody MonthlyEntries monthlyEntries) {
		return monthlyEntriesService.saveMonthlyEntry(monthlyEntries);
	}

	@GetMapping("/getAllMonthlyEntries")
	public Response getAllMonthlyEntries() {
		return monthlyEntriesService.getAllMonthlyEntries();
	}
	
	@PostMapping("/getAllMonthlyEntry")
	public Response getAllMonthlyEntry(@RequestBody PaginationWebModel paginationWebModel) {
		return monthlyEntriesService.getAllMonthlyEntry(paginationWebModel);
	}

	@GetMapping("/getMonthlyEntryById/{id}")
	public Optional<MonthlyEntries> getMonthlyEntryById(@PathVariable int id) {
		return monthlyEntriesService.getMonthlyEntryById(id);
	}

	@PutMapping("/updateMonthlyEntry")
	public Response updateMonthlyEntry(@RequestBody MonthlyEntries updatedMonthlyEntry) {
		return monthlyEntriesService.updateMonthlyEntry(updatedMonthlyEntry);
	}

	@DeleteMapping("/deleteMonthlyEntry/{id}")
	public Response deleteMonthlyEntry(@PathVariable int id) {
		return monthlyEntriesService.deleteMonthlyEntry(id);
	}

	@GetMapping("/by-project/{projectId}")
	public Response getByProjectId(@PathVariable int projectId) {
		return monthlyEntriesService.findByProjectId(projectId);
	}
	
	@PostMapping("/by-employee")
	public Response getByemployeeId(@RequestBody MonthlyEntryDTO dto) {
		return monthlyEntriesService.findByEmployeeId(dto);
	}

	@PostMapping("/monthlyEntriesFilter")
	public Response monthlyEntriesFilter(@RequestBody MonthlyEntryDTO dto) {
		return monthlyEntriesService.monthlyEntriesFilter(dto);
	}
	
//	@PostMapping("/monthlyEntriesFilters")
//	public Response monthlyEntriesFilters(@RequestBody MonthlyEntryDTO dto) {
//		return monthlyEntriesService.monthlyEntriesFilters(dto);
//	}
	
	@PostMapping("/projectUtilizationFilter")
	public Response projectUtilizationFilter(@RequestBody MonthlyEntryDTO dto) {
		return monthlyEntriesService.projectUtilizationFilter(dto);
	}
	
	@PostMapping("/getAllProjectUtilization")
	public Response getAllProjectUtilization(@RequestBody PaginationWebModel paginationWebModel) {
		return monthlyEntriesService.getAllProjectUtilization(paginationWebModel);
	}

	@PostMapping("/getAllConsolidatedData")
	public Response getAllConsolidatedData(@RequestBody PaginationWebModel paginationWebModel) {
		return monthlyEntriesService.getAllConsolidatedData(paginationWebModel);
	}

}
