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
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.service.MonthlyEntriesService;

@RestController
@RequestMapping("/monthlyEntries")	
@CrossOrigin
public class MonthlyEntriesController {
	
	@Autowired
	private MonthlyEntriesService monthlyEntriesService;
	
	@PostMapping("/saveMonthlyEntry")
	public Response saveMonthlyEntry(@RequestBody MonthlyEntries  monthlyEntries) {
		return monthlyEntriesService.saveMonthlyEntry(monthlyEntries);
	}
	
	@GetMapping("/getAllMonthlyEntries")
	public List<Map<String, Object>> getAllMonthlyEntries() {
        return monthlyEntriesService.getAllMonthlyEntries();
	}
	
	@GetMapping("/getMonthlyEntryById/{id}")
	public Optional<MonthlyEntries> getMonthlyEntryById(@PathVariable int id) {
        return monthlyEntriesService.getMonthlyEntryById(id);
	}
	
	@PutMapping("/updateMonthlyEntry")
	public Response  updateMonthlyEntry(@RequestBody MonthlyEntries updatedMonthlyEntry) {
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

}
